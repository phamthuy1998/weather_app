package thuy.ptithcm.weatherapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import thuy.ptithcm.weatherapp.R

class FragmentToday : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListener()
    }

    private fun onClickListener() {

    }

    fun getCurrentWeatherData(data: String) {
        // request wiil Run request that we send
        val requestQueue: RequestQueue = Volley.newRequestQueue(requireContext())
        val url =
            "https://samples.openweathermap.org/data/2.5/weather?q=" + data + "&appid=fcbda2aaa17984d153f7e29b14cc3bd3"
        val stringRequest: StringRequest =
            StringRequest(Request.Method.GET, url, Response.Listener {

            }, Response.ErrorListener {

            })
        requestQueue.add(stringRequest)

    }
}
