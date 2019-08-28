package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/1.
 */

public class pdfResponse implements Serializable {


    /**
     * contract : {"id":1,"pdf_url":"http://pdf.zhibohome.net/web/viewer.html?url=XXX.pdf"}
     */


    public  String residue;

    public String getResidue() {
        return residue;
    }

    public void setResidue(String residue) {
        this.residue = residue;
    }

    private ContractBean contract;

    public ContractBean getContract() {
        return contract;
    }

    public void setContract(ContractBean contract) {
        this.contract = contract;
    }

    public static class ContractBean {
        /**
         * id : 1
         * pdf_url : http://pdf.zhibohome.net/web/viewer.html?url=XXX.pdf
         */

        private int id;
        private String pdf_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPdf_url() {
            return pdf_url;
        }

        public void setPdf_url(String pdf_url) {
            this.pdf_url = pdf_url;
        }
    }
}

