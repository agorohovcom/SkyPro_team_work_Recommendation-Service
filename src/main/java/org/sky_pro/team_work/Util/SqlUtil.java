package org.sky_pro.team_work.Util;

public class SqlUtil {

    private SqlUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static final String findUserInfoSQL = """
                  select EXISTS(SELECT  FROM PRODUCTS p
                  JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                  WHERE t.USER_ID = ? and p.TYPE = 'CREDIT') as isHaveCredit,
                  EXISTS(SELECT  FROM PRODUCTS p
                  JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                  WHERE t.USER_ID = ? and p.TYPE = 'DEBIT') as isHaveDebit,
                  EXISTS(SELECT  FROM PRODUCTS p
                  JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                  WHERE t.USER_ID = ? and p.TYPE = 'INVEST') as isHaveInvest,
                  SUM(CASE WHEN p.TYPE = 'DEBIT' and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END) as debitDeposit,
                  SUM(CASE WHEN p.TYPE = 'DEBIT' and t.TYPE = 'WITHDRAW' THEN AMOUNT ELSE 0 END) as debitWithdraw,
                  SUM(CASE WHEN p.TYPE = 'SAVING' and t.TYPE = 'DEPOSIT' THEN AMOUNT ELSE 0 END) as savingDeposit
                  FROM PRODUCTS p
                  JOIN TRANSACTIONS t ON t.PRODUCT_ID = p.ID
                  WHERE USER_ID = ?
            """;
}
