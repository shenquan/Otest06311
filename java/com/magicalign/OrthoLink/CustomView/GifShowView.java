package com.magicalign.OrthoLink.CustomView;

import java.io.InputStream;
import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.FlagToString;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * @description 
 *              GifShowView是一个经过扩展的ImageView，它不仅继承了ImageView原生的所有功能，还加入了播放GIF动画的功能
 * 
 * @author guolin ,modified by hanshenquan
 * @CreateTime 2016-3-3
 */
public class GifShowView extends ImageView implements OnClickListener {

	/**
	 * 播放GIF动画的关键类
	 */
	private Movie mMovie;

	/**
	 * 开始播放按钮图片
	 */
	// private Bitmap mStartButton;

	/**
	 * 记录动画开始的时间
	 */
	private long mMovieStart;

	/**
	 * GIF图片的宽度
	 */
	private int mImageWidth;

	/**
	 * GIF图片的高度
	 */
	private int mImageHeight;

	/**
	 * 图片是否正在播放
	 */
	private boolean mIsPlaying = false;

	/**
	 * 是否允许自动播放
	 */
	private boolean mIsAutoPlay;

	/**
	 * 存储临时canvas
	 */
	private Canvas mCanvas;
	/**
	 * 是否为播放前的初始状态，默认为true
	 */
	private boolean mInitialState = true;
	/**
	 * 存储滑动seekbar时的播放时间
	 */
	private int mSeekBarPlayTime;
	/**
	 * 存储传递进来的seekbar对象
	 */
	private SeekBar mSeekBar = null;

	/**
	 * 点击暂停按钮后，再打开开始按钮时已经经过的时间
	 */
	private long mPassedTime;
	/**
	 * 是否点击过暂停按钮
	 */
	private boolean mIsPauseClicked = false;
	/**
	 * 获取传递进来的暂停ImageView
	 */

	private ImageView mImvPause = null;
	/**
	 * 获取传递进来的播放ImageView
	 */

	private ImageView mImvPlay = null;
	/**
	 * 临时存储点击暂停后的的时间
	 */
	private long mClickPausePlayTime;
	/**
	 * 记录正常播放的进度
	 */
	private int mPlayTime;

	/**
	 * 点击播放按钮的时候才动态更新的进度条标识，但是手动拖动seekbar的时候停止播放了gif，所以应该设置个标记，防止两者冲突
	 */
	// private boolean mClickPlayFlag = true;
	/**
	 * 是否为SeekBar拖动后，然后再点击开始
	 */
	private boolean mIsClickPlayImageAfterMoveSeekBarByHand = false;

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * PowerImageView构造函数。
	 * 
	 * @param context
	 */
	public GifShowView(Context context) {
		super(context);
		// View 级别的硬件加速效果关闭
		this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}

	/**
	 * PowerImageView构造函数。
	 * 
	 * @param context
	 */
	public GifShowView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// View 级别的硬件加速效果关闭
		this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}

	/**
	 * PowerImageView构造函数，在这里完成所有必要的初始化操作。
	 * 
	 * @param context
	 */
	public GifShowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// View 级别的硬件加速效果关闭
		this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		
		int resourceId = 0;
		for (int i = 0; i < attrs.getAttributeCount(); i++) {
			if (attrs.getAttributeName(i).equals("src")) {
				resourceId = attrs.getAttributeResourceValue(i, 0);
				break;
			}
		}
		if (resourceId != 0) {
			// 当资源id不等于0时，就去获取该资源的流
			InputStream is = getResources().openRawResource(resourceId);
			// 使用Movie类对流进行解码
			mMovie = Movie.decodeStream(is);
			if (mMovie != null) {
				// 如果返回值不等于null，就说明这是一个GIF图片，下面获取是否自动播放的属性

				Bitmap bitmap = BitmapFactory.decodeStream(is);
				mImageWidth = bitmap.getWidth();
				mImageHeight = bitmap.getHeight();
				bitmap.recycle();

			}
		}

	}

	/**
	 * 由于要在下面加播放和暂停按钮，所以此处暂时用不到该函数
	 */
	@Override
	public void onClick(View v) {
		/*
		 * if (v.getId() == getId()) { // 当用户点击图片时，开始播放GIF动画 mIsPlaying = true;
		 * invalidate(); }
		 */
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mCanvas = canvas;
		if (mMovie == null) {
			// mMovie等于null，说明是张普通的图片，则直接调用父类的onDraw()方法
			super.onDraw(mCanvas);
		} else {
			// mMovie不等于null，说明是张GIF图片
			if (mIsAutoPlay) {
				// 如果允许自动播放，就调用playMovie()方法播放GIF动画
				playMovie(mCanvas);
				invalidate();
			} else {
				// 不允许自动播放时，判断当前图片是否正在播放
				if (mIsPlaying) {
					// 正在播放就继续调用playMovie()方法，一直到动画播放结束为止
					if (playMovie(mCanvas)) {
						// 播放结束
						mIsPlaying = false;
						// 回到初始化时间
						mSeekBarPlayTime = 0;
						// 设置mFirstPlay=true
						mInitialState = true;

					}
					invalidate();
				} else {
					// 当为初始状态时，初始化
					if (mInitialState == true) {
						// 还没开始播放就只绘制GIF图片的第一帧
						mMovie.setTime(0);
						mMovie.draw(mCanvas, 0, 0);

						// 当播放结束时，变换按钮
						mImvPause.setVisibility(8);
						mImvPlay.setVisibility(0);

					} else {
						// 不是初始化状态，即暂停状态或者是拖动时的状态
						mMovie.setTime(mSeekBarPlayTime);
						mMovie.draw(mCanvas, 0, 0);

					}

				}
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (mMovie != null) {
			// 如果是GIF图片则重写设定PowerImageView的大小
			setMeasuredDimension(mImageWidth, mImageHeight);
		}
	}

	/**
	 * 开始播放GIF动画，播放完成返回true，未完成返回false。
	 * 
	 * @param canvas
	 * @return 播放完成返回true，未完成返回false。
	 */
	private boolean playMovie(Canvas canvas) {
		long now = SystemClock.uptimeMillis();
		if (mMovieStart == 0) {
			mMovieStart = now;
		}

		if (mIsPauseClicked) {
			// 中间时间有间隔，相当于起始时间延后相同的时间
			mMovieStart += mPassedTime;
			// 将暂停后的时间设置为0
			mPassedTime = 0;
			// 设置为未点击过暂停按钮
			mIsPauseClicked = false;
		}

		if (mIsClickPlayImageAfterMoveSeekBarByHand == true) {
			mMovieStart = SystemClock.uptimeMillis() - mSeekBarPlayTime;
			mIsClickPlayImageAfterMoveSeekBarByHand = false;

		}
		int duration = mMovie.duration();
		if (duration == 0) {
			duration = 1000;
		}
		mPlayTime = (int) ((now - mMovieStart) % duration);

		mMovie.setTime(mPlayTime);
		mMovie.draw(canvas, 0, 0);

		// 播放的时候动态更新进度条
		int progress = (int) (mPlayTime * 1.0 / duration * 255);
		mSeekBar.setProgress(progress);
		mIsClickPlayImageAfterMoveSeekBarByHand = false;

		if ((now - mMovieStart) >= duration) {
			mMovieStart = 0;
			return true;
		}
		return false;
	}

	/**
	 * 设置Gif播放的进度
	 * 
	 * @param progress
	 *            获取进度值
	 */
	public void setGifPlayProgress(int progress) {

		// 设置为非初始化状态
		mInitialState = false;
		// 标记为手动拖动了SeekBar
		mIsClickPlayImageAfterMoveSeekBarByHand = true;
		// 用进度条拖动的时候停止播放Gif
		// mIsPlaying = false;
		int duration = mMovie.duration();
		// 全局的mSeekBarPlayTime先改变，然后再调用的onDraw中的mMovie.setTime(mSeekBarPlayTime);
		mSeekBarPlayTime = (int) ((progress * 1.0 / 255) * duration);
		// 只有是GifShowView自动对于SeekBar做出改变的时候

		invalidate();// 回调onDraw函数
	}

	/**
	 * 获取Orthodontics Activity中的SeekBar对象，用作播放的时候动态更新进度条
	 * 
	 * @seekBar 将要获取的SeekBar
	 */
	public void getSeekBar(SeekBar seekBar) {
		if (mSeekBar == null) {
			mSeekBar = seekBar;
		}
	}

	/**
	 * 获取Orthodontics Activity中的两个按钮对象，以对其状态做更改
	 */
	public void getPlayAndPauseObj(ImageView imvPlay, ImageView imvPause) {
		if (mImvPlay == null && mImvPause == null) {
			mImvPlay = imvPlay;
			mImvPause = imvPause;
			// mHaveGotImageView = true;

		}
	}

	/**
	 * 点击播放按钮时，播放GIf图
	 */

	public void palyGif() {
		if (mIsPlaying == false) {
			mIsPlaying = true;
			// 如果曾点击过暂停按钮
			if (mIsPauseClicked) {
				mPassedTime = SystemClock.uptimeMillis() - mClickPausePlayTime;
			}

			invalidate();
		}
	}

	/**
	 * 点击暂停按钮时，暂停GIf图
	 */
	public void pauseGif() {
		mIsPlaying = false;
		mIsPauseClicked = true;
		mClickPausePlayTime = SystemClock.uptimeMillis();

	}

}
