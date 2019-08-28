package shangri.example.com.shangri.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.PlatFormAdapter;
import shangri.example.com.shangri.adapter.StarAdapter;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.presenter.CompanyMainPresent;
import shangri.example.com.shangri.presenter.view.CompanyMainView;
import shangri.example.com.shangri.util.TakeTurnPhotos;

/**
 * Description:
 * Data：2018/11/8-10:45
 * Author: lin
 */
public class CompanyDisplayFragment extends BaseFragment<CompanyMainView, CompanyMainPresent> implements CompanyMainView {
    @BindView(R.id.platform)
    RecyclerView re_platform;

    @BindView(R.id.star)
    RecyclerView re_star;

    @BindView(R.id.ll_platform)
    LinearLayout ll_platform;
    @BindView(R.id.ll_star)
    LinearLayout ll_star;
    @BindView(R.id.ll_photos)
    LinearLayout ll_photos;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi1;
    @BindView(R.id.ll_data)
    LinearLayout ll_data;
    @BindView(R.id.company_photos)
    ConvenientBanner company_photos;
    //公司相册只有一张
    @BindView(R.id.iv_one)
    ImageView iv_one;
    private StarAdapter starAdapter;
    private PlatFormAdapter platFormAdapter;
    List<CompanyMainBean.CompanyAnchor> anchorList = new ArrayList<>();
    List<CompanyMainBean.CompanyPlatfrom> platfromList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_company_display;
    }
    @Override
    protected int getErrorLayoutId() {
        return  R.layout.fragment_company_display;
    }
    @Override
    protected CompanyMainPresent createPresenter() {
        return new CompanyMainPresent(getActivity(),this);
    }

    @Override
    protected void initViewsAndEvents() {
        mPresenter.getCompanyMain();
        initAdapter();
    }

    private void initAdapter() {
        starAdapter = new StarAdapter(getActivity(), R.layout.item_paltform_star, anchorList);
        re_star.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        re_platform.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        platFormAdapter = new PlatFormAdapter(getActivity(), R.layout.item_paltform_star2, platfromList);
        re_platform.setAdapter(platFormAdapter);
        re_star.setAdapter(starAdapter);
    }

    @Override
    public void getCompanyMain(CompanyMainBean bean) {
        if (!bean.getCompany_anchor().isEmpty()) {
            ll_star.setVisibility(View.VISIBLE);
            starAdapter.addAll(bean.getCompany_anchor());
        }else {
            ll_star.setVisibility(View.GONE);
        }
        if (!bean.getCompany_platfrom().isEmpty()) {
            ll_platform.setVisibility(View.VISIBLE);
            platFormAdapter.addAll(bean.getCompany_platfrom());
        }else {
            ll_platform.setVisibility(View.GONE);
        }
        if (!bean.getCompany_photo().isEmpty()){
            ll_photos.setVisibility(View.VISIBLE);
            if (bean.getCompany_photo().size()>1){
                iv_one.setVisibility(View.GONE);
                company_photos.setVisibility(View.VISIBLE);
                new TakeTurnPhotos(getActivity(), bean.getCompany_photo(), company_photos, new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.e("onItemClick", "点击了图片");
                    }
                });
            }else {
                iv_one.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load(bean.getCompany_photo().get(0).getImg_url()).fitCenter().into(iv_one);
                company_photos.setVisibility(View.GONE);
            }

//            new TakeTurnPhotos(getActivity(), bean.getCompany_photo(), company_photos, new OnItemClickListener() {
//                @Override
//                public void onItemClick(int position) {
//                    Log.e("onItemClick", "点击了图片"+position);
//                }
//            });
        }else {
            ll_photos.setVisibility(View.GONE);
        }
       if (bean.getCompany_anchor().isEmpty()&&bean.getCompany_platfrom().isEmpty()&&bean.getCompany_photo().isEmpty()){
           ll_data.setVisibility(View.GONE);
           activity_empty_linshi1.setVisibility(View.VISIBLE);
       }else {
           activity_empty_linshi1.setVisibility(View.GONE);
           ll_data.setVisibility(View.VISIBLE);
       }
    }

    @Override
    public void enterPlatfrom() {

    }

    @Override
    public void setStarAnchor() {

    }

    @Override
    public void upPhotoAlbum() {

    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void deleteImg() {

    }
}
