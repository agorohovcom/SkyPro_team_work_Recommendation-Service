package org.sky_pro.team_work.util;

import org.sky_pro.team_work.enums.ComparisonType;

import java.util.UUID;

public class SqlUtil {

    private SqlUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String isUserExistsById() {
        return """
                SELECT EXISTS(SELECT 1 FROM users WHERE id = ?)
                """;
    }

    public static String userOf() {
        return """
                SELECT TOP 1 EXISTS(SELECT 1 FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = ? and p.TYPE = ?)
                FROM PRODUCTS
                """;
    }

    public static String activeUserOf() {
        return """
                SELECT CASE WHEN COUNT(?) > 5 THEN TRUE
                ELSE FALSE END FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = ?
                """;
    }

    public static String transactionSumCompare(ComparisonType comparison) {
        return String.format("""
                SELECT SUM(CASE WHEN p.TYPE = ? and t.TYPE = ? THEN AMOUNT ELSE 0 END)
                %s ? FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = ?
                """, comparison.getOperator());
    }

    public static String transactionSumCompareDepositWithdraw(ComparisonType comparison) {
        return String.format("""
                SELECT SUM(CASE WHEN p.TYPE = ? and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END) %s
                SUM(CASE WHEN p.TYPE = ? and t.TYPE = 'WITHDRAW' THEN AMOUNT ELSE 0 END)
                FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = ?
                """, comparison.getOperator());
    }

    public static String productOrProductTransactionTypeSumMoreMoreValue(ComparisonType comparison1,
                                                                         ComparisonType comparison2) {
        return String.format("""
                SELECT SUM(CASE WHEN p.TYPE = ? and t.TYPE = ? THEN AMOUNT ELSE 0 END) %s ? or
                SUM(CASE WHEN p.TYPE = ? and t.TYPE = ? THEN AMOUNT ELSE 0 END) %s ?
                FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = ?
                """, comparison1.getOperator(), comparison2.getOperator());
    }
}