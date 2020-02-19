package thuy.ptithcm.weatherapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import thuy.ptithcm.weatherapp.*
import thuy.ptithcm.weatherapp.adapter.MyFragmentPagerAdapter
import thuy.ptithcm.weatherapp.fragment.FragmentSevenDay
import thuy.ptithcm.weatherapp.fragment.FragmentToday
import thuy.ptithcm.weatherapp.fragment.FragmentTomorrow
import thuy.ptithcm.weatherapp.fragment.SearchAddressFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_address.isSelected = true
        tv_address.isSingleLine= true

        val adapter =
            MyFragmentPagerAdapter(this.supportFragmentManager)
        adapter.addFragment(FragmentToday(), getString(R.string.today))
        adapter.addFragment(FragmentTomorrow(), getString(R.string.tomorrow))
        adapter.addFragment(FragmentSevenDay(), getString(R.string._day_after))
        view_pg_main.adapter = adapter
        view_pg_main.setOffscreenPageLimit(1)
        tab_day_main.setupWithViewPager(view_pg_main)

        onClickListener()
    }

    private fun onClickListener() {
        tv_address.setOnClickListener {
            getCurrentWeatherData("Saigon")
            showFragment(SearchAddressFragment())
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.actitity_main, fragment)
            .commit()
    }


    fun getCurrentWeatherData(data: String) {
        // request wiil Run request that we send
        val requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val url =
            "https://samples.openweathermap.org/data/2.5/weather?q=" + data + "&appid=fcbda2aaa17984d153f7e29b14cc3bd3"
        val stringRequest: StringRequest =
            StringRequest(Request.Method.GET, url, Response.Listener {
                var jsonObject:JSONObject = JSONObject(it)
                var dayMonth = jsonObject.getString("dt")
                var nameCity = jsonObject.getString("name")

            }, Response.ErrorListener {

            })
        requestQueue.add(stringRequest)

    }

}
