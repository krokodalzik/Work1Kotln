import jdk.jfr.Category

class Expense(val amount: Double, var category: String, val date: String) {

    fun displayExpense() {
        println("Amount: $amount, Category: $category, date: $date");

    }
}

class ExpenseManager() {

    private val expenses = mutableListOf<Expense>()
    fun showExpenses() {
        if (expenses != null) {
            for (expense in expenses) {
                expense.displayExpense()
            }
        }
    }

    fun addExpense(expense: Expense) {
        expenses.add(expense)
    }

    fun calculateTotals(): Map<String, Double> {
        val totalsByCategory = mutableMapOf<String, Double>()

        expenses.forEach { expense ->
            val accumulatedTotal = totalsByCategory.getOrDefault(expense.category, 0.0)
            totalsByCategory[expense.category] = accumulatedTotal + expense.amount
        }

        return totalsByCategory
    }
}

fun main() {
    val expenseManager = ExpenseManager()

    expenseManager.addExpense(Expense(100.0, "Food", "2023-10-01"))
    expenseManager.addExpense(Expense(50.0, "Transport", "2023-10-02"))
    expenseManager.addExpense(Expense(200.0, "Food", "2023-10-03"))
    expenseManager.addExpense(Expense(75.0, "Entertainment", "2023-10-04"))

    println("All Expenses:")
    expenseManager.showExpenses()

    println("\nTotal Expenses by Category:")
    val totalsByCategory = expenseManager.calculateTotals()
    for ((category, total) in totalsByCategory) {
        println("$category: $total")
    }
}