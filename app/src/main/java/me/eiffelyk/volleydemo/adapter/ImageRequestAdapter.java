package me.eiffelyk.volleydemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import me.eiffelyk.volleydemo.R;
import me.eiffelyk.volleydemo.util.VolleyUtil;

public class ImageRequestAdapter extends ImageBaseAdapter {

	private Context context;

	public ImageRequestAdapter(Context context, String[] imageUrlArray) {
		super(context, imageUrlArray);
		this.context=context;
	}

	@Override
	int getItemLayout() {
		return R.layout.fr_image_request_list_item;
	}

	@Override
	void setImage(final ImageView imageView, final String imageUrl) {
		
		//设置空图片
		imageView.setImageResource(R.mipmap.ic_empty);
		
		//取消这个ImageView已有的请求
		VolleyUtil.getQueue(context).cancelAll(imageView);


		ImageRequest request = new ImageRequest("url", new Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap response) {
				imageView.setImageBitmap(response);
			}
		}, 0, 0, ImageView.ScaleType.CENTER, Config.RGB_565, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("馋猫",error.toString());
			}
		});
		request.setTag(imageView);
		
		VolleyUtil.getQueue(this.context).add(request);
		
	}

}
