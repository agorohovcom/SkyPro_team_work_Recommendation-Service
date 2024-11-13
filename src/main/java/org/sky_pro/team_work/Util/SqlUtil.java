package org.sky_pro.team_work.Util;

import org.sky_pro.team_work.enums.ComparisonType;
import org.sky_pro.team_work.enums.ProductType;
import org.sky_pro.team_work.enums.TransactionType;

import java.util.UUID;

public class SqlUtil {

    private SqlUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static final String checkInvest500 = """            
            SELECT EXISTS(SELECT 1 FROM PRODUCTS p
            JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
            WHERE t.USER_ID = ? and p.TYPE = 'DEBIT')
            AND NOT EXISTS(SELECT 1 FROM PRODUCTS p
            JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
            WHERE t.USER_ID = ? and p.TYPE = 'INVEST')
            AND SUM(CASE WHEN p.TYPE = 'SAVING' and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END) > 1000
            FROM PRODUCTS p
            JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
            WHERE USER_ID = ?
            """;
    public static final String checkTopSaving = """            
            SELECT EXISTS(SELECT 1 FROM PRODUCTS p
            JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
            WHERE t.USER_ID = ? and p.TYPE = 'DEBIT')
            AND (SUM(CASE WHEN p.TYPE = 'DEBIT' and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END)  >= 50000
            OR SUM(CASE WHEN p.TYPE = 'SAVING' and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END) >= 50000)
            AND SUM(CASE WHEN p.TYPE = 'DEBIT' and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END)  >
            SUM(CASE WHEN p.TYPE = 'DEBIT' and t.TYPE = 'WITHDRAW' THEN AMOUNT ELSE 0 END)
            FROM PRODUCTS p
            JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
            WHERE USER_ID = ?
            """;

    public static final String checkSimpleCredit = """
            SELECT  NOT EXISTS(SELECT 1 FROM PRODUCTS p
            JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
            WHERE t.USER_ID = ? and p.TYPE = 'CREDIT')
            AND SUM(CASE WHEN p.TYPE = 'DEBIT' and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END) >
            SUM(CASE WHEN p.TYPE = 'DEBIT' and t.TYPE = 'WITHDRAW' THEN AMOUNT ELSE 0 END)
            AND SUM(CASE WHEN p.TYPE = 'DEBIT' and t.TYPE = 'WITHDRAW' THEN AMOUNT ELSE 0 END) > 100000
            FROM PRODUCTS p JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
            WHERE USER_ID = ?
            """;

    public static String userOf(UUID user, ProductType type) {
        return String.format("""
                SELECT TOP 1 EXISTS(SELECT 1 FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = %s and p.TYPE = %s)
                FROM PRODUCTS
                """, user, type);
    }


    public static String activeUserOf(ProductType productType, UUID user) {
        return String.format("""
                SELECT CASE WHEN COUNT(%s) > 5 THEN TRUE
                ELSE FALSE END FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = %s
                """, productType, user);
    }

    public static String transactionSumCompare(ProductType productType, TransactionType transactionType,
                                               ComparisonType comparison, Integer compareNumber, UUID user) {
        return String.format("""
                SELECT SUM(CASE WHEN p.TYPE = %s and t.TYPE = %s THEN AMOUNT ELSE 0 END)
                %s %d FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = %s
                """, productType, transactionType, comparison, compareNumber, user);
    }

    public static String transactionSumCompareDepositWithdraw(ProductType productType, ComparisonType comparison,
                                                              UUID user) {
        return String.format("""
                SELECT SUM(CASE WHEN p.TYPE = %s and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END) %s
                SUM(CASE WHEN p.TYPE = %s' and t.TYPE = 'WITHDRAW' THEN AMOUNT ELSE 0 END)
                FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = %s, productType, transactionType, comparison, compareNumber, user);
                """, productType, productType, comparison, user);
    }
}
