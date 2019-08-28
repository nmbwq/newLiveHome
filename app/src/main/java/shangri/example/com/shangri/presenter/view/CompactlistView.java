package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.ChairmanSignResponse;
import shangri.example.com.shangri.model.bean.response.CompactDetailResponse;
import shangri.example.com.shangri.model.bean.response.CompactListResponse;
import shangri.example.com.shangri.model.bean.response.CompactMobanResponse;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.model.bean.response.pdfResponse;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface CompactlistView extends BaseView {

    //
    void legalGuildContract(CompactListResponse resultBean);

    void legalDetail(CompactDetailResponse resultBean);

    void templateList(CompactMobanResponse resultBean);

    void legalIsface(IsFaceResponse resultBean);

    void legalTemplate(IsFaceResponse resultBean);

    void legalContract_signature(ChairmanSignResponse resultBean);

    void legalContractVerify();
    void legalanchorVerify();


    void ontractVerifyCode();

    void legalTxcontractPush();

    void legalAnchor_signature(ChairmanSignResponse resultBean);

    void legalTxcontract();

    void legalContractRepeal(ChairmanSignResponse resultBean);

    void legalContractBase(pdfResponse resultBean);
    void signNumber(pdfResponse resultBean);

}
