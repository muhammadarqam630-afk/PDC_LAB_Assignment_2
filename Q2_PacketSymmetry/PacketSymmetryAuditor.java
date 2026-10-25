package Q2_PacketSymmetry;

import java.util.Scanner;

public class PacketSymmetryAuditor {

    public static boolean verify(String packet) {

        if (packet == null || packet.isEmpty()) {
            return false;
        }

        TransactionStack stack =
                new TransactionStack(packet.length());

        RequestQueue queue =
                new RequestQueue(packet.length());

        for (int i = 0; i < packet.length(); i++) {
            char current = packet.charAt(i);
            stack.push(current);
            queue.enqueue(current);
        }

        boolean symmetric = true;
        int check = 1;

        System.out.println("\n===== VERIFICATION LOG =====");

        while (!stack.isEmpty() && !queue.isEmpty()) {

            char fromStack = stack.pop();
            char fromQueue = queue.dequeue();

            boolean match = fromStack == fromQueue;

            System.out.println(
                "Check " + check +
                " | Stack: " + fromStack +
                " | Queue: " + fromQueue +
                " | Match: " + match
            );

            if (!match) {
                symmetric = false;
            }
            check++;
        }

        return symmetric;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== PACKET SYMMETRY AUDITOR =====");
        System.out.print("Enter packet payload: ");

        String packet = scanner.nextLine();

        if (packet.isEmpty()) {
            System.out.println("ERROR: Empty packet payload");
        } else {
            boolean result = verify(packet);
            System.out.println(
                result ? "RESULT: SYMMETRY VERIFIED"
                       : "RESULT: SYMMETRY FAILED"
            );
        }

        scanner.close();
    }
}