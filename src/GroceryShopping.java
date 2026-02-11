import java.util.Scanner;

// Custom exception class for item not found
class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}

public class GroceryShopping {
    public static void main(String[] args) {

        String[] item = {
            "Apple", "Banana", "Bread", "Milk", "Eggs",
            "Cheese", "Chicken", "Rice", "Pasta", "Tomato"
        };

        float[] price = {
            0.50f, 0.30f, 2.00f, 1.50f, 2.50f,
            3.00f, 5.00f, 1.00f, 1.20f, 0.80f
        };

        Scanner scanner = new Scanner(System.in);

        while (true) { // multiple users loop
            float totalBill = 0.0f;
            System.out.println("\n🛒 New Shopping Session Started");

            while (true) { // single user loop
                try {
                    System.out.print("Enter item name (or type 'finish'): ");
                    String inputItem = scanner.nextLine();

                    if (inputItem.equalsIgnoreCase("finish")) {
                        System.out.println("✅ Your total bill is: $" + totalBill);
                        System.out.println("Thank you for shopping!\n");
                        break;
                    }

                    int itemIndex = -1;
                    for (int i = 0; i < item.length; i++) {
                        if (item[i].equalsIgnoreCase(inputItem)) {
                            itemIndex = i;
                            break;
                        }
                    }

                    if (itemIndex == -1) {
                        throw new ItemNotFoundException("❌ Item '" + inputItem + "' not found.");
                    }

                    System.out.print("Enter quantity for " + item[itemIndex] + ": ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    if (quantity <= 0) {
                        System.out.println("Quantity must be positive.");
                        continue;
                    }

                    float itemCost = price[itemIndex] * quantity;
                    totalBill += itemCost;

                    System.out.println("Added " + quantity + " x " + item[itemIndex] +
                            " → $" + itemCost + " | Total: $" + totalBill);

                } catch (ItemNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("⚠ Invalid input. Please enter a number for quantity.");
                    scanner.nextLine(); // clear bad input
                }
            }

            System.out.print("Type 'exit' to quit or press Enter for new customer: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("👋 Program ended. Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}
