package com.example.resortmanagement.Room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import com.google.gson.Gson

import kotlinx.android.synthetic.main.fragment_edit_room.*
import kotlinx.android.synthetic.main.fragment_edit_room.btnSaveRoom
import kotlinx.android.synthetic.main.fragment_edit_room.inputRoomType
import kotlinx.android.synthetic.main.fragment_edit_room.roomPerson
import kotlinx.android.synthetic.main.fragment_edit_room.roomPrice
import kotlinx.android.synthetic.main.fragment_edit_room.switchStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [editRoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class editRoomFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var id:String
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_edit_room, container, false)
//         Toast.makeText(this.context,this.id.toString(),Toast.LENGTH_LONG).show()

        return v
    }

    private fun updateRoom(data:HashMap<String,Any>){
        val load =  SweetAlertDialog(this.context, SweetAlertDialog.PROGRESS_TYPE)
            .showCancelButton(false)
            .setTitleText("loading...")

        val success = SweetAlertDialog(this.context, SweetAlertDialog.SUCCESS_TYPE)
            .showCancelButton(false)

        val error = SweetAlertDialog(this.context, SweetAlertDialog.ERROR_TYPE)
            .showCancelButton(false)
        load.show()

        viewModel.updateRoom(data){status ->
            if(status){
                load.hide()
                success.titleText = "Success to create"
                success.show()
            }else{
                load.hide()
                error.titleText = "Fail to update room"
                error.show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = hashMapOf<String,Any>("roomID" to this.id)
        val load =  SweetAlertDialog(this.context, SweetAlertDialog.PROGRESS_TYPE)
            .showCancelButton(false)
            .setTitleText("loading...")

        val success = SweetAlertDialog(this.context, SweetAlertDialog.SUCCESS_TYPE)
            .showCancelButton(false)

        val error = SweetAlertDialog(this.context, SweetAlertDialog.ERROR_TYPE)
            .showCancelButton(false)

        viewModel.getRoomByID(data){

            load.show()

            if(it){
                load.hide()
                success.show()
                success.titleText = "ดึงข้อมูลสำเร็จ"
                val json = Gson().toJson(viewModel.allRoom.value)
                val data = viewModel.allRoom.value
                txtRooomID.text = data?.get(0)?._id.toString()
                inputRoomType.setText(data?.get(0)?.roomType.toString())
                roomPrice.setText(data?.get(0)?.price.toString())
                roomPerson.setText(data?.get(0)?.person.toString())
                switchStatus.isChecked = data?.get(0)?.room_status.toBoolean()
//                Toast.makeText(this.context,data.toString(),Toast.LENGTH_LONG).show()
            }else{
                load.hide()
                error.show()
            }
        }

        btnSaveRoom.setOnClickListener {
            if(roomPerson.text.isNullOrEmpty() or roomPrice.text.isNullOrEmpty() or txtRooomID.text.isNullOrEmpty() or inputRoomType.text.isNullOrEmpty()){
                Toast.makeText(this.context,"Having field is null or empty",Toast.LENGTH_LONG).show()
            }else{
                var check = if(switchStatus.isChecked){
                    "True"
                }else{
                    "False"
                }

                val data = hashMapOf<String,Any>("roomID" to txtRooomID.text.toString(),"roomType" to inputRoomType.text.toString(),"person" to roomPerson.text.toString().toInt() ,"price" to roomPrice.text.toString().toDouble(),"room_status" to check)
                updateRoom(data)
            }
        }
    }

    companion object {

        fun newInstance(idR:String) = editRoomFragment().apply {
            arguments = Bundle().apply {
                id = idR
            }
        }
    }
}