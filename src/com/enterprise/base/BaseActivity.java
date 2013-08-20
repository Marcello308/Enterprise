package com.enterprise.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Message;
import android.view.Gravity;
import android.widget.Toast;

import com.enterprise.R;
import com.enterprise.utils.LTToolUtil;
import com.enterprise.utils.exception.LTDBException;
import com.enterprise.utils.exception.LTHttpException;
import com.enterprise.utils.exception.LTParserException;
import com.enterprise.utils.http.LTHttpError;
import com.enterprise.utils.http.LTHttpRequestMessage;

public class BaseActivity extends Activity{
		
	// ///////////////////////////////////////////////////////////////////
	// 显示加载数据的对话框
	// ///////////////////////////////////////////////////////////////////

	protected ProgressDialog progressDialog = null;

	/**
	 * Destribution：显示默认的加载数据对话框
	 */
	public void showProgressDialog() {
		showProgressDialog(LTToolUtil.getResourceString(R.string.loading_message));
	}

	/**
	 * Destribution：显示加载对话框，并且显示的信息为message
	 * 
	 * @param message
	 */
	public void showProgressDialog(String message) {
		if (this.progressDialog == null) {
			this.progressDialog = new ProgressDialog(this);
			this.progressDialog.setCanceledOnTouchOutside(false);
			this.progressDialog.setIndeterminate(true);
			
		}
		if (LTToolUtil.isNull(message))
			this.progressDialog.setMessage(LTToolUtil.getResourceString(R.string.loading_message));
		else
			this.progressDialog.setMessage(message);
		this.progressDialog.show();
	}

	/**
	 * Destribution：关闭加载进度对话框
	 */
	public void dismissProgressDialog() {
		if (this.progressDialog != null) {
			this.progressDialog.dismiss();
			this.progressDialog = null;
		}
	}
	// ///////////////////////////////////////////////////////////////////
	// 显示加载数据的对话框
	// ///////////////////////////////////////////////////////////////////
	private Toast _toast;

    public void showMessage(int textId) {
        showMessage(LTToolUtil.getResourceString(textId));
    }

    public void showMessage(CharSequence text) {
        if (_toast != null) {
            _toast = null;
        }
        _toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        _toast.setGravity(Gravity.CENTER, 0, 0);
        _toast.setText(text);
        _toast.show();
    }
    
    
    
    protected void loadDataWithMessage(String message, LTHttpRequestMessage httpMessage) {
        new PostMessageTask(message).execute(httpMessage);
    }


    private class PostMessageTask extends AsyncTask<LTHttpRequestMessage, Void, Object> {

        LTHttpRequestMessage _hm;
        String message;

        public PostMessageTask(String message) {
            this.message = message;
        }


        @Override
        protected void onPreExecute() {
            if (this.message == null)
                showProgressDialog();
            else
                showProgressDialog(this.message);
        }


        @Override
        protected Object doInBackground(LTHttpRequestMessage... params) {
            
            _hm = params[0];
            Object obj = null;
            LTHttpError error = new LTHttpError();
			try {
				obj = _hm.getWebService().httpPost(params[0]);
			} catch (LTHttpException e) {
				e.printStackTrace();
				error.errorMessage = e.getMessage();
			} catch (LTParserException e) {
				e.printStackTrace();
				error.errorMessage = e.getMessage();
				obj = error;
			} catch (LTDBException e) {
				error.errorMessage = e.getMessage();
				
			} catch (Exception e) {
				error.errorMessage = (e==null || LTToolUtil.isNull(e.getMessage()))?
						LTToolUtil.getResourceString(R.string.unknown_error):e.getMessage();
			}
			if (!LTToolUtil.isNull(error.errorMessage)) {
				obj = error;
			}
			
            return obj;
        }

        @Override
        protected void onPostExecute(Object obj) {

            dismissProgressDialog();
            Message message = new Message();
            message.what = _hm.getHandlerMessageID();
            message.obj = obj;
            _hm.getHandler().sendMessage(message);
        }
    }
}
