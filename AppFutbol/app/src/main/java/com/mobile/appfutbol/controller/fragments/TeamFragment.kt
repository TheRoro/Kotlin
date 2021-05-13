package com.mobile.appfutbol.controller.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.appfutbol.R
import com.mobile.appfutbol.adapter.TeamAdapter
import com.mobile.appfutbol.controller.activities.TeamDetails
import com.mobile.appfutbol.model.ApiResponseHeader
import com.mobile.appfutbol.model.Team
import com.mobile.appfutbol.network.TeamService
import kotlinx.android.synthetic.main.fragment_team.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamFragment : Fragment(), TeamAdapter.OnItemClickListener {

    var team: List<Team> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = rvTeams
        loadTeams(view.context)
    }
    private fun loadTeams(context: Context) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-football-v1.p.rapidapi.com/v2/teams/league/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Declaramos nustro objeto TeamService

        val teamService: TeamService

        teamService = retrofit.create(TeamService::class.java)

        val request = teamService.getTeams(
            "api-football-v1.p.rapidapi.com",
            "d229813befmsh4c1646ad132a0b5p1313fcjsn9afecaefc97e"

        )

        request.enqueue(object : Callback<ApiResponseHeader> {

            override fun onFailure(call: Call<ApiResponseHeader>, t: Throwable) {
                Log.d("Activity Fail", "Error:"+t.toString())
            }

            override fun onResponse(
                call: Call<ApiResponseHeader>,
                response: Response<ApiResponseHeader>
            ) {
                if(response.isSuccessful) {
                    val teams: List<Team> = response.body()!!.api.teams ?: ArrayList()
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = TeamAdapter(teams, context, this@TeamFragment)
                }
                else {
                    Log.d("Activity Fail", "Error: "+response.code())
                }
            }
        })

    }

    override fun onItemClicked(team: Team) {
        val intent = Intent(context, TeamDetails::class.java)
        intent.putExtra("Team", team)
        startActivity(intent)
    }
}