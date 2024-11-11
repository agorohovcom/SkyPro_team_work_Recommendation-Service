package org.sky_pro.team_work.Util;

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
}
