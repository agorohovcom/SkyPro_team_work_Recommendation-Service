package org.sky_pro.team_work.domain;

import lombok.Data;


@Data
public class UserInfoForRules {
    private boolean isHaveCredit;
    private boolean isHaveDebit;
    private boolean isHaveInvest;
    private int debitDeposit;
    private int debitWithdraw;
    private int savingDeposit;
}
