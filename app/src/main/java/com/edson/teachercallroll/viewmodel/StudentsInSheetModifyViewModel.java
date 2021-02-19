package com.edson.teachercallroll.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.edson.teachercallroll.apidata.network.RetroClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsInSheetModifyViewModel extends ViewModel {

    private int httpStatusCode;
    public MutableLiveData<String> assistanceList = new MutableLiveData<>();

    public StudentsInSheetModifyViewModel() {
    }

    public MutableLiveData<String> showAssistanceSheetDetails(String token, long idSheet) {
        Call<ResponseBody> call = RetroClient.getInstance().getApi().showAssistanceSheetDetails(token, idSheet);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        assistanceList.postValue(response.body().string());
                    } else {
                        assistanceList.postValue(null);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                httpStatusCode = response.code();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return assistanceList;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

}
