package seedu.address.financialtracker.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.financialtracker.model.expense.Country;
import seedu.address.financialtracker.model.expense.Expense;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Encapsulate expense lists into financial tracker and allow accessing expense list accordingly by indicating
 * which country that the user wanna access.
 */
public class FinancialTracker {

    private String currentCountry;
    private final ObservableMap<String, ExpenseList> expenseListMap = FXCollections.observableHashMap();
    private final ObservableMap<String, ExpenseList> internalUnmodifiableExpenseListMap =
            FXCollections.unmodifiableObservableMap(expenseListMap);

    public FinancialTracker() {
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            expenseListMap.put(obj.getDisplayCountry(), new ExpenseList(obj.getDisplayCountry()));
        }
        currentCountry = "Singapore"; //Singapore as default
    }

    public String getCurrentCountry() {
        return currentCountry;
    }

    /**
     * Changes current country key.
     * @param country
     */
    public void setCurrentCountry(String country) {
        if (expenseListMap.containsKey(country)) {
            currentCountry = country;
        } else {
            currentCountry = "Singapore";
        }

    }

    /**
     * Adds an expense into the expense list associated with current country key.
     */
    public void addExpense(Expense expense) {
        assert expenseListMap.get(currentCountry) != null;
        expense.setCountry(new Country(currentCountry));
        expenseListMap.get(currentCountry).addExpense(expense);
    }

    /**
     * Adds an expense into the expense list associated with another country key.
     */
    public void addExpense(Expense expense, Country country) {
        expenseListMap.get(country.value).addExpense(expense);
    }

    /**
     * Deletes an expense from the expense list associated with current coountry key.
     */
    public void deleteExpense(int index) {
        assert expenseListMap.get(currentCountry) != null;
        expenseListMap.get(currentCountry).deleteExpense(index);
    }

    /**
     * Returns an underlying Map.
     */
    public ObservableMap<String, ExpenseList> getInternalUnmodifiableExpenseListMap() {
        return internalUnmodifiableExpenseListMap;
    }

    /**
     * Edits an expense in the expense list associated with current country key.
     */
    public void setExpense(Expense expenseToEdit, Expense editedExpense) throws CommandException {
        requireNonNull(editedExpense);
        editedExpense.setCountry(new Country(currentCountry));
        expenseListMap.get(currentCountry).setExpense(expenseToEdit, editedExpense);
    }

    public HashMap<String, Double> getSummaryMap() {
        HashMap<String, Double> summaryMap = new HashMap<>();
        double total = 0;
        for (String key : internalUnmodifiableExpenseListMap.keySet()) {
            if (!internalUnmodifiableExpenseListMap.get(key).isEmpty()) {
                double summary = internalUnmodifiableExpenseListMap.get(key).getSummary();
                summaryMap.put(key, summary);
                total += summary;
            }
        }
        summaryMap.put("Total", total);
        return summaryMap;
    }
}
