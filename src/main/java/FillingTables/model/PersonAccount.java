package FillingTables.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PersonAccount {
    private Integer id; // номер квартиры (personalAccount)

    private double initialSaldo;

    /**
     * Далее идут зачисления по месяцам
     */
    private double chargeJanuary;
    private double chargeFebruary;
    private double chargeMarch;
    private double chargeApril;
    private double chargeMay;
    private double chargeJune;
    private double chargeJuly;
    private double chargeAugust;
    private double chargeSeptember;
    private double chargeOctober;
    private double chargeNovember;
    private double chargeDecember;

    /**
     * Далее идут платежи по месяцам
     */

    private double paymentJanuary;
    private double paymentFebruary;
    private double paymentMarch;
    private double paymentApril;
    private double paymentMay;
    private double paymentJune;
    private double paymentJuly;
    private double paymentAugust;
    private double paymentSeptember;
    private double paymentOctober;
    private double paymentNovember;
    private double paymentDecember;

    @Override
    public String toString() {
        return "PersonAccount{" +
                "id=" + id +
                ", initialSaldo=" + initialSaldo +
                ", chargeJanuary=" + chargeJanuary +
                ", chargeFebruary=" + chargeFebruary +
                ", chargeMarch=" + chargeMarch +
                ", chargeApril=" + chargeApril +
                ", chargeMay=" + chargeMay +
                ", chargeJune=" + chargeJune +
                ", chargeJuly=" + chargeJuly +
                ", chargeAugust=" + chargeAugust +
                ", chargeSeptember=" + chargeSeptember +
                ", chargeOctober=" + chargeOctober +
                ", chargeNovember=" + chargeNovember +
                ", chargeDecember=" + chargeDecember +
                ", paymentJanuary=" + paymentJanuary +
                ", paymentFebruary=" + paymentFebruary +
                ", paymentMarch=" + paymentMarch +
                ", paymentApril=" + paymentApril +
                ", paymentMay=" + paymentMay +
                ", paymentJune=" + paymentJune +
                ", paymentJuly=" + paymentJuly +
                ", paymentAugust=" + paymentAugust +
                ", paymentSeptember=" + paymentSeptember +
                ", paymentOctober=" + paymentOctober +
                ", paymentNovember=" + paymentNovember +
                ", paymentDecember=" + paymentDecember +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public double getInitialSaldo() {
        return initialSaldo;
    }

    public double getChargeJanuary() {
        return chargeJanuary;
    }

    public double getChargeFebruary() {
        return chargeFebruary;
    }

    public double getChargeMarch() {
        return chargeMarch;
    }

    public double getChargeApril() {
        return chargeApril;
    }

    public double getChargeMay() {
        return chargeMay;
    }

    public double getChargeJune() {
        return chargeJune;
    }

    public double getChargeJuly() {
        return chargeJuly;
    }

    public double getChargeAugust() {
        return chargeAugust;
    }

    public double getChargeSeptember() {
        return chargeSeptember;
    }

    public double getChargeOctober() {
        return chargeOctober;
    }

    public double getChargeNovember() {
        return chargeNovember;
    }

    public double getChargeDecember() {
        return chargeDecember;
    }

    public double getPaymentJanuary() {
        return paymentJanuary;
    }

    public double getPaymentFebruary() {
        return paymentFebruary;
    }

    public double getPaymentMarch() {
        return paymentMarch;
    }

    public double getPaymentApril() {
        return paymentApril;
    }

    public double getPaymentMay() {
        return paymentMay;
    }

    public double getPaymentJune() {
        return paymentJune;
    }

    public double getPaymentJuly() {
        return paymentJuly;
    }

    public double getPaymentAugust() {
        return paymentAugust;
    }

    public double getPaymentSeptember() {
        return paymentSeptember;
    }

    public double getPaymentOctober() {
        return paymentOctober;
    }

    public double getPaymentNovember() {
        return paymentNovember;
    }

    public double getPaymentDecember() {
        return paymentDecember;
    }

    public Map<String, Double> getAllCharges() {

        Map<String, Double> charges = new LinkedHashMap<>(); // гарантирует порядок
        charges.put("January", chargeJanuary);
        charges.put("February", chargeFebruary);
        charges.put("March", chargeMarch);
        charges.put("April", chargeApril);
        charges.put("May", chargeMay);
        charges.put("June", chargeJune);
        charges.put("July", chargeJuly);
        charges.put("August", chargeAugust);
        charges.put("September", chargeSeptember);
        charges.put("October", chargeOctober);
        charges.put("November", chargeNovember);
        charges.put("December", chargeDecember);
        return charges;
    }

    public Map<String, Double> getAllPayments() {
        Map<String, Double> payments = new LinkedHashMap<>(); // гарантирует порядок
        payments.put("January", paymentJanuary);
        payments.put("February", paymentFebruary);
        payments.put("March", paymentMarch);
        payments.put("April", paymentApril);
        payments.put("May", paymentMay);
        payments.put("June", paymentJune);
        payments.put("July", paymentJuly);
        payments.put("August", paymentAugust);
        payments.put("September", paymentSeptember);
        payments.put("October", paymentOctober);
        payments.put("November", paymentNovember);
        payments.put("December", paymentDecember);
        return payments;
        //return new double[]{paymentJanuary, paymentFebruary, paymentMarch, paymentApril, paymentMay, paymentJune, paymentJuly, paymentAugust, paymentSeptember, paymentOctober, paymentNovember, paymentDecember};
    }

    public PersonAccount() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInitialSaldo(double initialSaldo) {
        this.initialSaldo = initialSaldo;
    }

    public void setChargeJanuary(double chargeJanuary) {
        this.chargeJanuary = chargeJanuary;
    }

    public void setChargeFebruary(double chargeFebruary) {
        this.chargeFebruary = chargeFebruary;
    }

    public void setChargeMarch(double chargeMarch) {
        this.chargeMarch = chargeMarch;
    }

    public void setChargeApril(double chargeApril) {
        this.chargeApril = chargeApril;
    }

    public void setChargeMay(double chargeMay) {
        this.chargeMay = chargeMay;
    }

    public void setChargeJune(double chargeJune) {
        this.chargeJune = chargeJune;
    }

    public void setChargeJuly(double chargeJuly) {
        this.chargeJuly = chargeJuly;
    }

    public void setChargeAugust(double chargeAugust) {
        this.chargeAugust = chargeAugust;
    }

    public void setChargeSeptember(double chargeSeptember) {
        this.chargeSeptember = chargeSeptember;
    }

    public void setChargeOctober(double chargeOctober) {
        this.chargeOctober = chargeOctober;
    }

    public void setChargeNovember(double chargeNovember) {
        this.chargeNovember = chargeNovember;
    }

    public void setChargeDecember(double chargeDecember) {
        this.chargeDecember = chargeDecember;
    }

    public void setPaymentJanuary(double paymentJanuary) {
        this.paymentJanuary = paymentJanuary;
    }

    public void setPaymentFebruary(double paymentFebruary) {
        this.paymentFebruary = paymentFebruary;
    }

    public void setPaymentMarch(double paymentMarch) {
        this.paymentMarch = paymentMarch;
    }

    public void setPaymentApril(double paymentApril) {
        this.paymentApril = paymentApril;
    }

    public void setPaymentMay(double paymentMay) {
        this.paymentMay = paymentMay;
    }

    public void setPaymentJune(double paymentJune) {
        this.paymentJune = paymentJune;
    }

    public void setPaymentJuly(double paymentJuly) {
        this.paymentJuly = paymentJuly;
    }

    public void setPaymentAugust(double paymentAugust) {
        this.paymentAugust = paymentAugust;
    }

    public void setPaymentSeptember(double paymentSeptember) {
        this.paymentSeptember = paymentSeptember;
    }

    public void setPaymentOctober(double paymentOctober) {
        this.paymentOctober = paymentOctober;
    }

    public void setPaymentNovember(double paymentNovember) {
        this.paymentNovember = paymentNovember;
    }

    public void setPaymentDecember(double paymentDecember) {
        this.paymentDecember = paymentDecember;
    }
}
