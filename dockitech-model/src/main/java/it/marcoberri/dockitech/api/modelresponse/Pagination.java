package it.marcoberri.dockitech.api.modelresponse;

public class Pagination {
    
    private int totEle = 0;
    private int elePerPage = 0;
    private int totPage = 0;
    public int getTotEle() {
        return totEle;
    }
    public void setTotEle(int totEle) {
        this.totEle = totEle;
    }
    public int getElePerPage() {
        return elePerPage;
    }
    public void setElePerPage(int elePerPage) {
        this.elePerPage = elePerPage;
    }
    public int getTotPage() {
        return totPage;
    }
    public void setTotPage(int totPage) {
        this.totPage = totPage;
    }
    

}
