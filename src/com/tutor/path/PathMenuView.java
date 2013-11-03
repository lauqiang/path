package com.tutor.path;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 超级仿path菜单 position定义菜单的位置，目前支持：左上;右上;右下;左下四个方向。 menuResIds定义出现的菜单的资源ID
 */
public class PathMenuView extends FrameLayout {

	private static final int LEFT_TOP = 0;

	private static final int RIGHT_TOP = 1;

	private static final int RIGHT_BOTTOM = 2;

	private static final int LEFT_BOTTOM = 3;

	/**
	 * 默认的位置是在右下角.
	 */
	private int position = 2;

	/**
	 * 那个圆形菜单.
	 */
	private ImageView mHome;

	/**
	 * 上下文.
	 */
	private Context mContext;

	/**
	 * 设备的宽度.
	 */
	private int mWIDTH = 0;

	/**
	 * 设备的高度.
	 */
	private int mHEIGHT = 0;

	/**
	 * 设备的density.
	 */
	private float mDensity;

	/**
	 * 菜单是否显示.
	 */
	private boolean bMenuShow;

	private static int xOffset = 15;
	private static int yOffset = -13;

	/**
	 * 菜单的资源个数.
	 */
	private int[] menuResIds = { R.drawable.composer_camera,
			R.drawable.composer_music, R.drawable.composer_sleep };

	// R.drawable.composer_music, R.drawable.composer_place

	public PathMenuView(Context context) {
		super(context);
		setupViews();
	}

	public PathMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.PathMenuView);

		position = a.getInt(R.styleable.PathMenuView_position, 3);

		a.recycle();
		setupViews();
	}

	private void setupViews() {
		mContext = getContext();

		mHEIGHT = mContext.getResources().getDisplayMetrics().heightPixels;
		mWIDTH = mContext.getResources().getDisplayMetrics().widthPixels;
		mDensity = mContext.getResources().getDisplayMetrics().density;

		xOffset = (int) (10.667 * mDensity);
		yOffset = (int) (8.667 * mDensity);

		mHome = new ImageView(mContext);

		mHome.setImageResource(R.drawable.composer_button);
		mHome.setOnClickListener(listener);

		addView(mHome);

		LayoutParams mHomeparams = (FrameLayout.LayoutParams) mHome
				.getLayoutParams();
		mHomeparams.width = LayoutParams.WRAP_CONTENT;
		mHomeparams.height = LayoutParams.WRAP_CONTENT;

		switch (position) {
		case LEFT_TOP:
			mHomeparams.gravity = Gravity.LEFT | Gravity.TOP;
			for (int i = 0; i < menuResIds.length; i++) {

				int width_padding = mWIDTH / ((menuResIds.length - 1) * 2);
				int height_padding = mHEIGHT / ((menuResIds.length - 1) * 2);

				ImageView imageView = new ImageView(mContext);
				imageView.setImageResource(menuResIds[i]);
				addView(imageView);
				LayoutParams params = (FrameLayout.LayoutParams) imageView
						.getLayoutParams();
				params.width = LayoutParams.WRAP_CONTENT;
				params.height = LayoutParams.WRAP_CONTENT;
				params.leftMargin = mWIDTH / 2
						- ((menuResIds.length - i - 1) * width_padding);
				params.topMargin = mHEIGHT / 2 - i * height_padding;
				params.gravity = Gravity.LEFT | Gravity.TOP;
				imageView.setLayoutParams(params);

			}

			break;
		case RIGHT_TOP:
			mHomeparams.gravity = Gravity.RIGHT | Gravity.TOP;
			for (int i = 0; i < menuResIds.length; i++) {

				int width_padding = mWIDTH / ((menuResIds.length - 1) * 2);
				int height_padding = mHEIGHT / ((menuResIds.length - 1) * 2);

				ImageView imageView = new ImageView(mContext);
				imageView.setImageResource(menuResIds[i]);
				addView(imageView);
				LayoutParams params = (FrameLayout.LayoutParams) imageView
						.getLayoutParams();
				params.width = LayoutParams.WRAP_CONTENT;
				params.height = LayoutParams.WRAP_CONTENT;
				params.rightMargin = mWIDTH / 2
						- ((menuResIds.length - i - 1) * width_padding);
				params.topMargin = mHEIGHT / 2 - i * height_padding;
				params.gravity = Gravity.RIGHT | Gravity.TOP;
				imageView.setLayoutParams(params);

			}
			break;
		case RIGHT_BOTTOM:
			mHomeparams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
			for (int i = 0; i < menuResIds.length; i++) {

				int width_padding = mWIDTH / ((menuResIds.length - 1) * 2);
				int height_padding = mHEIGHT / ((menuResIds.length - 1) * 2);

				ImageView imageView = new ImageView(mContext);
				imageView.setImageResource(menuResIds[i]);
				addView(imageView);
				LayoutParams params = (FrameLayout.LayoutParams) imageView
						.getLayoutParams();
				params.width = LayoutParams.WRAP_CONTENT;
				params.height = LayoutParams.WRAP_CONTENT;
				params.rightMargin = mWIDTH / 2
						- ((menuResIds.length - i - 1) * width_padding);

				params.bottomMargin = mHEIGHT / 2 - i * height_padding;

				params.gravity = Gravity.RIGHT | Gravity.BOTTOM;
				imageView.setLayoutParams(params);

			}
			break;
		case LEFT_BOTTOM:
			mHomeparams.gravity = Gravity.LEFT | Gravity.BOTTOM;
			for (int i = 0; i < menuResIds.length; i++) {

				int width_padding = mWIDTH / ((menuResIds.length - 1) * 2);
				int height_padding = mHEIGHT / ((menuResIds.length - 1) * 2);

				ImageView imageView = new ImageView(mContext);
				imageView.setImageResource(menuResIds[i]);
				addView(imageView);
				LayoutParams params = (FrameLayout.LayoutParams) imageView
						.getLayoutParams();
				params.width = LayoutParams.WRAP_CONTENT;
				params.height = LayoutParams.WRAP_CONTENT;
				params.leftMargin = mWIDTH / 2
						- ((menuResIds.length - i - 1) * width_padding);
				params.bottomMargin = mHEIGHT / 2 - i * height_padding;
				params.gravity = Gravity.LEFT | Gravity.BOTTOM;
				imageView.setLayoutParams(params);
			}
			break;
		default:
			break;
		}

		mHome.setLayoutParams(mHomeparams);
	}

	private OnClickListener listener = new OnClickListener() {

		public void onClick(View v) {
			if (!bMenuShow) {
				startAnimationIn(PathMenuView.this, 300);
			} else {
				startAnimationOut(PathMenuView.this, 300);
			}
			bMenuShow = !bMenuShow;
		}
	};

	/**
	 * 菜单隐藏动画.
	 * 
	 * @param group
	 * @param duration
	 */
	private void startAnimationIn(ViewGroup group, int duration) {
		for (int i = 1; i < group.getChildCount(); i++) {
			ImageView imageview = (ImageView) group.getChildAt(i);
			imageview.setVisibility(0);
			MarginLayoutParams mlp = (MarginLayoutParams) imageview
					.getLayoutParams();

			Animation animation = null;

			switch (position) {
			case LEFT_TOP:
				animation = new TranslateAnimation(0F, -mlp.leftMargin
						+ xOffset, 0F, -mlp.topMargin + yOffset);
				break;
			case RIGHT_TOP:
				animation = new TranslateAnimation(mlp.rightMargin - xOffset,
						0F, -mlp.topMargin + yOffset, 0F);
				break;
			case LEFT_BOTTOM:
				animation = new TranslateAnimation(0F, -mlp.leftMargin
						+ xOffset, 0F, -yOffset + mlp.bottomMargin);
				break;

			case RIGHT_BOTTOM:
				animation = new TranslateAnimation(mlp.rightMargin - xOffset,
						0F, -yOffset + mlp.bottomMargin, 0F);
				break;
			default:
				break;
			}

			animation.setFillAfter(true);
			animation.setDuration(duration);
			animation.setStartOffset((i * 100) / (-1 + group.getChildCount()));
			animation.setInterpolator(new OvershootInterpolator(2F));
			imageview.startAnimation(animation);

		}
	}

	/**
	 * 菜单显示动画.
	 * 
	 * @param group
	 * @param duration
	 */
	private void startAnimationOut(ViewGroup group, int duration) {
		for (int i = 1; i < group.getChildCount(); i++) {
			final ImageView imageview = (ImageView) group.getChildAt(i);
			MarginLayoutParams mlp = (MarginLayoutParams) imageview
					.getLayoutParams();

			Animation animation = null;

			switch (position) {
			case LEFT_TOP:
				animation = new TranslateAnimation(-mlp.leftMargin + xOffset,
						0F, -mlp.topMargin + yOffset, 0F);
				break;
			case RIGHT_TOP:
				animation = new TranslateAnimation(0F, mlp.rightMargin
						- xOffset, 0F, -mlp.topMargin + yOffset);
				break;

			case LEFT_BOTTOM:
				animation = new TranslateAnimation(-mlp.leftMargin + xOffset,
						0F, -yOffset + mlp.bottomMargin, 0F);
				break;

			case RIGHT_BOTTOM:
				animation = new TranslateAnimation(0F, mlp.rightMargin
						- xOffset, 0F, -yOffset + mlp.bottomMargin);
				break;
			default:
				break;
			}

			animation.setFillAfter(true);
			animation.setDuration(duration);
			animation.setStartOffset(((group.getChildCount() - i) * 100)
					/ (-1 + group.getChildCount()));
			animation.setInterpolator(new AnticipateInterpolator(2F));
			imageview.startAnimation(animation);
		}
	}

}