/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jollydays.booking.control;

/**
 *
 * @author gunter reinitzer
 */
public class PaymentStatistic {

    private int voucherOkCounter = 0;
    private int statusNokCounter = 0;
    private int invoiceNotFoundCounter = 0;
    private int errorCounter = 0;

    public PaymentStatistic() {
    }

    public int getVoucherOkCounter() {
        return voucherOkCounter;
    }

    public void incVoucherOkCounter(int count) {
        voucherOkCounter = voucherOkCounter + count;
    }

    public int getStatusNokCounter() {
        return statusNokCounter;
    }

    public void incStatusNokCounter(int count) {
        statusNokCounter = statusNokCounter + count;
    }

    public int getInvoiceNotFoundCounter() {
        return invoiceNotFoundCounter;
    }

    public void incInvoiceNotFoundCounter(int count) {
        invoiceNotFoundCounter = invoiceNotFoundCounter + count;
    }

    public int getErrorCounter() {
        return errorCounter;
    }

    public void incErrorCounter(int count) {
        errorCounter = errorCounter + count;
    }

    public void addStatistic(PaymentStatistic statistik) {
        incVoucherOkCounter(statistik.getVoucherOkCounter());
        incStatusNokCounter(statistik.getStatusNokCounter());
        incInvoiceNotFoundCounter(statistik.getInvoiceNotFoundCounter());
        incErrorCounter(statistik.getErrorCounter());
    }
}
