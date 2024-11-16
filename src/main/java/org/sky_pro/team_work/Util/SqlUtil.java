package org.sky_pro.team_work.Util;

import org.sky_pro.team_work.enums.ComparisonType;
import org.sky_pro.team_work.enums.ProductType;
import org.sky_pro.team_work.enums.TransactionType;

import java.util.UUID;

public class SqlUtil {

    private SqlUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String userOf(UUID user, ProductType type) {
        return String.format("""
                SELECT TOP 1 EXISTS(SELECT 1 FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = '%s' and p.TYPE = '%s')
                FROM PRODUCTS
                """, user, type);
    }

    public static String activeUserOf(ProductType productType, UUID user) {
        return String.format("""
                SELECT CASE WHEN COUNT('%s') > 5 THEN TRUE
                ELSE FALSE END FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = '%s'
                """, productType, user);
    }

    public static String transactionSumCompare(ProductType productType, TransactionType transactionType,
                                               ComparisonType comparison, Integer compareNumber, UUID user) {
        return String.format("""
                SELECT SUM(CASE WHEN p.TYPE = '%s' and t.TYPE = '%s' THEN AMOUNT ELSE 0 END)
                %s %d FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = '%s'
                """, productType, transactionType, comparison.getOperator(), compareNumber, user);
    }

    public static String transactionSumCompareDepositWithdraw(ProductType productType, ComparisonType comparison,
                                                              UUID user) {
        System.out.println(comparison);
        return String.format("""
                SELECT SUM(CASE WHEN p.TYPE = '%s' and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END) %s
                SUM(CASE WHEN p.TYPE = '%s' and t.TYPE = 'WITHDRAW' THEN AMOUNT ELSE 0 END)
                FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = '%s'
                """, productType, comparison.getOperator(), productType, user);
    }

    public static String debitSumOrSavingSumMoreFifty(ProductType productType1, TransactionType transactionType,
                                                      ComparisonType comparisonOr,
                                                      ProductType productType2, TransactionType transactionType1,
                                                      ComparisonType comparison, Integer compareNumber, UUID user) {
        return String.format("""
                SELECT SUM(CASE WHEN p.TYPE ='%s' and t.TYPE ='%s' THEN AMOUNT ELSE 0 END) %s
                SUM(CASE WHEN p.TYPE = '%s' and t.TYPE = %s THEN AMOUNT ELSE 0 END)
                %s %d FROM PRODUCTS p
                JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                WHERE t.USER_ID = '%s'
                """, productType1, transactionType, comparisonOr.getOperator(), productType2,transactionType1,
                comparison, compareNumber, user);

    }
}
