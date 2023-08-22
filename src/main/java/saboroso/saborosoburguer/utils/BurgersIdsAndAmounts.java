package saboroso.saborosoburguer.utils;

public class BurgersIdsAndAmounts {
    public Long firstBurgerId;
    public Long firstBurgerAmount;
    public Long secondBurgerId;
    public Long secondBurgerAmount;

    public BurgersIdsAndAmounts(String[] idsAndAmounts) {
        String[] firstBurger = idsAndAmounts[0].split(",");
        this.firstBurgerId = Long.parseLong(firstBurger[0]);
        this.firstBurgerAmount = Long.parseLong(firstBurger[1]);
        String[] secondBurger = idsAndAmounts[1].split(",");
        this.secondBurgerId = Long.parseLong(secondBurger[0]);
        this.secondBurgerAmount = Long.parseLong(secondBurger[1]);
    }
}

