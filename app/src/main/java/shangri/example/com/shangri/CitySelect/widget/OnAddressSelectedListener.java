package shangri.example.com.shangri.CitySelect.widget;


import shangri.example.com.shangri.CitySelect.bean.City;
import shangri.example.com.shangri.CitySelect.bean.County;
import shangri.example.com.shangri.CitySelect.bean.Province;
import shangri.example.com.shangri.CitySelect.bean.Street;

public interface OnAddressSelectedListener {
    void onAddressSelected(Province province, City city, County county, Street street);
}
