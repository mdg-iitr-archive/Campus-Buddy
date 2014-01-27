package in.co.sdslabs.iitr.Multi;

import in.co.sdslabs.iitr.ShikharParser.CheckNtwkCon;
import in.co.sdslabs.iitr.ShikharParser.ShikharParser;
import in.co.sdslabs.iitr.database.DBAdapter;
import in.co.sdslabs.iitr.zoom.DynamicZoomControl;
import in.co.sdslabs.iitr.zoom.ImageZoomView;
import in.co.sdslabs.iitr.zoom.ZoomState;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.util.Linkify;
import android.util.FloatMath;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class TabPage extends Activity implements OnTabChangeListener,
		OnItemSelectedListener, OnClickListener, OnTouchListener {

	private TabHost th;
	private Spinner spinnerTelDirectory1, spinnerTelDirectory2, spinnerMap;
	LinearLayout llScrollTelDirectory, llSpinnerMap, llSpinnerAdvanced;
	Gallery gallery;
	ImageView iv[] = new ImageView[4];
	private ImageZoomView mMapView;
	private LinearLayout llScrollWona, llParent, llScrollImages;
	private String[][] s;
	String[] contents_info;
	private String venue;
	private PopupPanel panel, panel_map;
	ImageView bCloseTelDirectory, bShowMapTelDirectory, bCallTelDirectory,
			bMailTelDirectory, bCloseMap, bCallMap, bMailMap;
	ProgressDialog pdia;
	DownloadEventsXml dis;

	Integer[][] images = {
			{ R.drawable.no_image },
			{ R.drawable.map021, R.drawable.map022, R.drawable.map023,
					R.drawable.map024 },
			{ R.drawable.map031, R.drawable.map032, R.drawable.map033 },
			{ R.drawable.map041, R.drawable.map042 },
			{ R.drawable.map051, R.drawable.map052, R.drawable.map053,
					R.drawable.map054 },
			{ R.drawable.map061 },
			{ R.drawable.map071, R.drawable.map072 },
			{ R.drawable.map081, R.drawable.map082, R.drawable.map083 },
			{ R.drawable.map091, R.drawable.map092, R.drawable.map093,
					R.drawable.map094 },
			{ R.drawable.map101, R.drawable.map102 },
			{ R.drawable.map111 },
			{ R.drawable.map121, R.drawable.map122, R.drawable.map123 },
			{ R.drawable.map131, R.drawable.map132 },
			{ R.drawable.no_image },
			{ R.drawable.map151, R.drawable.map152 },
			{ R.drawable.no_image },
			{ R.drawable.map171, R.drawable.map172, R.drawable.map173 },
			{ R.drawable.map181, R.drawable.map182, R.drawable.map183 },
			{ R.drawable.map191, R.drawable.map192 },
			{ R.drawable.map201, R.drawable.map202, R.drawable.map203 },
			{ R.drawable.map211, R.drawable.map212, R.drawable.map213,
					R.drawable.map214 },
			{ R.drawable.no_image },
			{ R.drawable.map231 },
			{ R.drawable.no_image },
			{ R.drawable.no_image },
			{ R.drawable.map261, R.drawable.map262 },
			{ R.drawable.map271 },
			{ R.drawable.map281, R.drawable.map282 },
			{ R.drawable.no_image },
			{ R.drawable.no_image },
			{ R.drawable.no_image },
			{ R.drawable.no_image },
			{ R.drawable.map331 },
			{ R.drawable.map331 },
			{ R.drawable.map351, R.drawable.map352, R.drawable.map353 },
			{ R.drawable.no_image },
			{ R.drawable.map371, R.drawable.map372, R.drawable.map373,
					R.drawable.map374 },
			{ R.drawable.map381 },
			{ R.drawable.map391 },
			{ R.drawable.map401 },
			{ R.drawable.map411 },
			{ R.drawable.map421 },
			{ R.drawable.map431 },
			{ R.drawable.map441 },
			{ R.drawable.map451, R.drawable.map452 },
			{ R.drawable.no_image },
			{ R.drawable.no_image },
			{ R.drawable.map481, R.drawable.map482, R.drawable.map483 },
			{ R.drawable.map491 },
			{ R.drawable.map501 },
			{ R.drawable.map511 },
			{ R.drawable.map521, R.drawable.map522, R.drawable.map523,
					R.drawable.map524 },
			{ R.drawable.map531, R.drawable.map532, R.drawable.map533,
					R.drawable.map534 },
			{ R.drawable.no_image },
			{ R.drawable.no_image },
			{ R.drawable.no_image },
			{ R.drawable.map571, R.drawable.map572, R.drawable.map573 },
			{ R.drawable.map581, R.drawable.map582, R.drawable.map583,
					R.drawable.map584 },
			{ R.drawable.map591, R.drawable.map592, R.drawable.map593,
					R.drawable.map594 },
			{ R.drawable.map601, R.drawable.map602, R.drawable.map603,
					R.drawable.map604 },
			{ R.drawable.map611, R.drawable.map612, R.drawable.map613,
					R.drawable.map614 },
			{ R.drawable.map621 },
			{ R.drawable.map631, R.drawable.map632 },
			{ R.drawable.no_image },
			{ R.drawable.map651, R.drawable.map652 },
			{ R.drawable.map661 },
			{ R.drawable.map671 },
			{ R.drawable.map681 },
			{ R.drawable.map691, R.drawable.map692, R.drawable.map693 },
			{ R.drawable.no_image },
			{ R.drawable.map711, R.drawable.map712, R.drawable.map713 },
			{ R.drawable.map721, R.drawable.map722, R.drawable.map723 },
			{ R.drawable.map731, R.drawable.map732, R.drawable.map733,
					R.drawable.map734 },
			{ R.drawable.no_image },
			{ R.drawable.map751 },
			{ R.drawable.map761, R.drawable.map762, R.drawable.map763 },
			{ R.drawable.no_image },
			{ R.drawable.map781 },
			{ R.drawable.no_image },
			{ R.drawable.map801, R.drawable.map802, R.drawable.map803 },
			{ R.drawable.map811, R.drawable.map812 },
			{ R.drawable.no_image },
			{ R.drawable.map831 },
			{ R.drawable.map841, R.drawable.map842 },
			{ R.drawable.no_image },
			{ R.drawable.map861, R.drawable.map862, R.drawable.map863,
					R.drawable.map864 },
			{ R.drawable.no_image },
			{ R.drawable.no_image },
			{ R.drawable.map891, R.drawable.map892, R.drawable.map893,
					R.drawable.map894 }, { R.drawable.no_image } };

	String p = null;

	private enum Mode {
		UNDEFINED, PAN, PINCHZOOM
	}

	/** Current listener mode */
	private Mode mMode = Mode.UNDEFINED;

	/** X-coordinate of previously handled touch event */
	private float mX;

	/** Y-coordinate of previously handled touch event */
	private float mY;

	/** X-coordinate of latest down event */
	private float mDownX;

	/** Y-coordinate of latest down event */
	private float mDownY;

	/** Velocity tracker for touch events */
	private VelocityTracker mVelocityTracker;

	long panAfterPinchTimeout;

	/** Distance touch can wander before we think it's scrolling */
	private int mScaledTouchSlop;

	/** Maximum velocity for fling */
	private int mScaledMaximumFlingVelocity;

	/** Boolean for checking Tap */
	private boolean isTap = false;

	private PointF mMidPoint = new PointF();
	private float oldDist = 1f;

	Bitmap mBitmap;

	/** Handling Touch times */
	private static int tapTimeOut = 120;
	private long downTime;

	private DynamicZoomControl mMapControl;
	private int id = -1;
	private boolean showTodayOnly = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_layout);

		spinnerTelDirectory1 = (Spinner) findViewById(R.id.spinnerPlace);
		spinnerTelDirectory2 = (Spinner) findViewById(R.id.spinnerAdvanced);
		spinnerMap = (Spinner) findViewById(R.id.spinnerMap);

		llScrollTelDirectory = (LinearLayout) findViewById(R.id.linearLayoutTelDirectory);
		llScrollWona = (LinearLayout) findViewById(R.id.linearLayoutWona);
		llSpinnerMap = (LinearLayout) findViewById(R.id.linearLayoutSearchSpinnerMap);
		llSpinnerAdvanced = (LinearLayout) findViewById(R.id.linearLayoutSpinnerAdvanced);
		llParent = (LinearLayout) findViewById(R.id.linearLayoutParent);
		llScrollImages = (LinearLayout) findViewById(R.id.linearLayoutImages);

		mMapView = (ImageZoomView) findViewById(R.id.mapView);

		panel = new PopupPanel(R.layout.popup);
		panel_map = new PopupPanel(R.layout.popup_map);
		View v = panel.getView();
		View v_map = panel_map.getView();
		bCloseTelDirectory = (ImageView) v.findViewById(R.id.buttonClose);
		// bShowMapTelDirectory = (Button) v.findViewById(R.id.buttonShowMap);
		bCallTelDirectory = (ImageView) v.findViewById(R.id.buttonCall);
		bMailTelDirectory = (ImageView) v.findViewById(R.id.buttonMail);
		bCloseMap = (ImageView) v_map.findViewById(R.id.buttonCloseMap);
		bCallMap = (ImageView) v_map.findViewById(R.id.buttonCallMap);
		bMailMap = (ImageView) v_map.findViewById(R.id.buttonMailMap);
		iv[0] = (ImageView) v_map.findViewById(R.id.image1);
		iv[1] = (ImageView) v_map.findViewById(R.id.image2);
		iv[2] = (ImageView) v_map.findViewById(R.id.image3);
		iv[3] = (ImageView) v_map.findViewById(R.id.image4);

		// initialise some constant variables
		mScaledTouchSlop = ViewConfiguration.get(getApplicationContext())
				.getScaledTouchSlop();
		mScaledMaximumFlingVelocity = ViewConfiguration.get(
				getApplicationContext()).getScaledMaximumFlingVelocity();

		mBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.iitrmap);

		mMapControl = new DynamicZoomControl();

		th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec specs = th.newTabSpec("TAG_WONA");
		specs.setContent(R.id.tabWona);
		specs.setIndicator("WONA Feeds",
				getResources().getDrawable(R.drawable.upcoming)); // add image
																	// to tab
		th.addTab(specs);
		specs = th.newTabSpec("TAG_TELEPHONE_DIRECTORY");
		specs.setContent(R.id.tabTelDirectory);
		specs.setIndicator("Telephone Directory",
				getResources().getDrawable(R.drawable.byvenue));
		th.addTab(specs);
		specs = th.newTabSpec("TAG_MAP");
		specs.setContent(R.id.tabMap);
		specs.setIndicator("Map",
				getResources().getDrawable(R.drawable.tab_map_view));
		th.addTab(specs);

		DBAdapter adapter = new DBAdapter(getApplicationContext());
		try {
			adapter.getInstance(getApplicationContext());
			adapter.createDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adapter.close();

		// setSpinner
		setSpinner();

		th.setCurrentTab(1);

		// set listeners
		th.setOnTabChangedListener(this);
		spinnerTelDirectory1.setOnItemSelectedListener(this);
		spinnerTelDirectory2.setOnItemSelectedListener(this);
		spinnerMap.setOnItemSelectedListener(this);

		/*
		 * bShowMapTelDirectory.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub panel.hide(); th.setCurrentTab(2); } });
		 */

		bCloseTelDirectory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				panel.hide();
			}
		});

		bCallTelDirectory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				panel.hide();
				try {
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:" + contents_info[1]));
					startActivity(callIntent);
				} catch (ActivityNotFoundException activityException) {
					Toast.makeText(getApplicationContext(), "Cannot Make Call",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		bMailTelDirectory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				panel.hide();

				// copy to clip board option
				ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				clipboard.setText(contents_info[2] + "@iitr.ernet.in");

				Toast.makeText(
						getApplicationContext(),
						"E-mail address: " + contents_info[2]
								+ "@iitr.ernet.in\n" + "Copied To Clipboard",
						Toast.LENGTH_SHORT).show();

				/*
				 * Intent intent = new Intent(Intent.ACTION_SEND);
				 * intent.putExtra(Intent.EXTRA_EMAIL, new String[] {
				 * contents_info[2] + "@iitr.ernet.in" }); try {
				 * startActivity(Intent.createChooser(intent, "Sending...")); }
				 * catch (ActivityNotFoundException activityException) { }
				 */
			}
		});

		bCloseMap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				panel_map.hide();
			}
		});

		bCallMap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				panel_map.hide();
				try {
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:" + contents_info[2]));
					startActivity(callIntent);
				} catch (ActivityNotFoundException activityException) {
					Toast.makeText(getApplicationContext(), "Cannot Make Call",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		bMailMap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				panel_map.hide();

				// copy to clip board option
				ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				clipboard.setText(contents_info[3] + "@iitr.ernet.in");

				Toast.makeText(
						getApplicationContext(),
						"E-mail address: " + contents_info[3]
								+ "@iitr.ernet.in\n" + "Copied To Clipboard",
						Toast.LENGTH_SHORT).show();

				/*
				 * Intent intent = new Intent(Intent.ACTION_SEND);
				 * intent.putExtra(Intent.EXTRA_EMAIL, new String[] {
				 * contents_info[3] + "@iitr.ernet.in" }); try {
				 * startActivity(Intent.createChooser(intent, "Sending...")); }
				 * catch (ActivityNotFoundException activityException) { }
				 */
			}
		});

	}

	private void setSpinner() {
		// set Spinners
		DBAdapter spinner = new DBAdapter(getApplicationContext());
		try {
			spinner.getInstance(getApplicationContext());
			spinner.createDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String spinnerTelDir1Values[] = spinner.getDataForTelDir1Spinner();
		String Places[] = spinner.getDataForMapSpinner();
		spinner.close();

		// events spinner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.marquee_text_view_layout,
				spinnerTelDir1Values);

		adapter.setDropDownViewResource(R.layout.spinner_xml);
		spinnerTelDirectory1.setAdapter(adapter);

		// map spinner
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_spinner_item, Places);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spinnerMap.setAdapter(adapter);
	}

	// ---------------set Spinner 2----------
	private void setSpinner2(String place) {
		// set Spinners
		DBAdapter spinner = new DBAdapter(getApplicationContext());
		try {
			spinner.getInstance(getApplicationContext());
			spinner.createDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String spinnerTelDir2Values[] = spinner.getDataForTelDir2Spinner(place);
		spinner.close();

		// events spinner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_spinner_item,
				spinnerTelDir2Values);
		adapter.setDropDownViewResource(R.layout.spinner_xml);
		spinnerTelDirectory2.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		/*
		 * switch(v.getId()){ case R.id.buttonCall: break; case
		 * R.id.buttonClose: break; case R.id.buttonMail: break; case
		 * R.id.buttonShowMap: break; case R.id.buttonCallMap: break; case
		 * R.id.buttonMailMap: break; case R.id.buttonCloseMap: break;
		 * 
		 * }
		 */

		id = v.getId();
		contents_info = s[id];
		// set dialog
		View view = panel.getView();

		// Set Text fiels in panel
		((TextView) view.findViewById(R.id.textTitleTelDirectory))
				.setText(spinnerTelDirectory1.getSelectedItem().toString());
		if ("".equals(contents_info[3])) {
			((TextView) view.findViewById(R.id.textSubTitle))
					.setText(contents_info[0]);
			contents_info[3] = null;
		} else {
			((TextView) view.findViewById(R.id.textSubTitle))
					.setText(contents_info[3]);
		}
		((TextView) view.findViewById(R.id.textNumber)).setText("Phone : "
				+ contents_info[1]);
		if (!"".equals(contents_info[2])) {
			((TextView) view.findViewById(R.id.textMailId))
					.setText("E- Mail : " + contents_info[2] + "@iitr.ernet.in");
			((TextView) view.findViewById(R.id.textMailId))
					.setVisibility(View.VISIBLE);
			((ImageView) view.findViewById(R.id.buttonMail))
					.setVisibility(View.VISIBLE);
		} else {
			((TextView) view.findViewById(R.id.textMailId))
					.setVisibility(View.GONE);
			((ImageView) view.findViewById(R.id.buttonMail))
					.setVisibility(View.GONE);
		}

		// ----chk condition for isOnMap----
		/*
		 * DBAdapter isOnMap = new DBAdapter(getApplicationContext()); try {
		 * isOnMap.getInstance(getApplicationContext());
		 * isOnMap.createDataBase(); } catch (SQLException e) {
		 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
		 * if(isOnMap.isOnMap(spinnerTelDirectory1.getSelectedItem().toString(),
		 * contents_info[3])) bShowMapTelDirectory.setVisibility(View.VISIBLE);
		 * else bShowMapTelDirectory.setVisibility(View.GONE); isOnMap.close();
		 */

		panel.show();

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		if (arg0.getId() == R.id.spinnerPlace) {
			String item = (String) spinnerTelDirectory1.getSelectedItem();
			if ("Select".equalsIgnoreCase(item)) {
				llSpinnerAdvanced.setVisibility(View.GONE);
				showTelData(item, null);
			}

			else {
				llSpinnerAdvanced.setVisibility(View.VISIBLE);
				// display data + set spinner advanced
				setSpinner2(item);
			}
		} else if (arg0.getId() == R.id.spinnerMap) {
			p = (String) spinnerMap.getSelectedItem();
			id = -2;
			onTabChanged(th.getCurrentTabTag());
		} else if (arg0.getId() == R.id.spinnerAdvanced) {
			String item1 = (String) spinnerTelDirectory1.getSelectedItem();
			String item2 = (String) spinnerTelDirectory2.getSelectedItem();
			if ("All".equalsIgnoreCase(item2))
				showTelData(item1, null);
			else {
				// display data
				showTelData(item1, item2);
			}
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabChanged(String tabId) {
		int currentTab = th.getCurrentTab();
		if (currentTab == 0) {
			id = -1;
			llScrollWona.removeAllViews();
			TextView tv = new TextView(this);
			tv.setTextColor(Color.BLACK);
			tv.setText("To be updated soon");
			tv.setPadding(10, 10, 0, 0);
			llScrollWona.addView(tv);
		} else if (currentTab == 1) {
			id = -1;
			// String item = (String) spinnerTelDirectory1.getSelectedItem();
		} else if (currentTab == 2) {
			ZoomState mZoomState = mMapControl.getZoomState();
			mMapView.setZoomState(mZoomState);
			mMapView.setImage(mBitmap);
			mMapView.setOnTouchListener(this);
			mMapControl.setAspectQuotient(mMapView.getAspectQuotient());
			resetZoomState();
			// check id
			PointF pan;
			if (id != -1) {
				if (id == -2)
					pan = getPlaceCoordinates(p);
				// check db to get rect for place
				else
					pan = getPlaceCoordinates(spinnerTelDirectory1
							.getSelectedItem().toString());

				if (pan == null) {
					mZoomState.setPanX((float) 0.5);
					mZoomState.setPanY((float) 0.5);
				} else {
					// set mapView to it
					mZoomState.setPanX(pan.x);
					mZoomState.setPanY(pan.y);
					mZoomState.setZoom(5);
					
					Log.v("x=", pan.x + "");
					Log.v("y=", pan.y + "");
					
				}
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.syncmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		if (item.getItemId() == R.id.search) {
			
			/*
			 * case R.id.toggleMenu: if (showTodayOnly) showTodayOnly = false; else
			 * showTodayOnly = true; onTabChanged(th.getCurrentTabTag()); break;
			 */

			/*
			 * case R.id.syncData: // set downloader ShikharParser sdCardStatus =
			 * new ShikharParser(); if (sdCardStatus.checkExternalMedia()) { dis =
			 * new DownloadEventsXml(); dis.execute(); } else { Toast msg =
			 * Toast.makeText(TabPage.this,
			 * "Please insert a SDcard. Cannot update without it. ",
			 * Toast.LENGTH_LONG); msg.show(); } break;
			 */
			
			th.setCurrentTab(2);
			if (!llSpinnerMap.isShown())
				llSpinnerMap.setVisibility(View.VISIBLE);
		}

		return false;
	}

	private PointF getPlaceCoordinates(String selection) {
		// TODO Auto-generated method stub
		PointF pan = new PointF();
		DBAdapter coordinates = new DBAdapter(getApplicationContext());
		try {
			coordinates.getInstance(getApplicationContext());
			coordinates.createDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pan = coordinates.searchPlaceForCoordinates(selection);
		coordinates.close();
		if (pan == null) {
			return null;
		}
		pan.x /= mBitmap.getWidth();
		pan.y /= mBitmap.getHeight();
		return pan;
	}

	private void resetZoomState() {
		mMapControl.getZoomState().setPanX(0.5f);
		mMapControl.getZoomState().setPanY(0.5f);
		mMapControl.getZoomState().setZoom(1f);
		mMapControl.getZoomState().notifyObservers();
	}

	// Handle onTouchEvent
	public boolean onTouch(View v, MotionEvent event) {
		final int action = event.getAction() & MotionEvent.ACTION_MASK;
		final float x = event.getX();
		final float y = event.getY();

		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);

		if (!(panel.isVisible || panel_map.isVisible)) {
			if (action == MotionEvent.ACTION_DOWN) {
				downTime = System.currentTimeMillis();
				mMapControl.stopFling();
				mDownX = x;
				mDownY = y;
				mX = x;
				mY = y;
			} else if (action == MotionEvent.ACTION_POINTER_DOWN) {
				oldDist = spacing(event);
				if (event.getPointerCount() > 1) {
					oldDist = spacing(event);
					if (oldDist > 10f) {
						midPoint(mMidPoint, event);
						mMode = Mode.PINCHZOOM;
					}
				}
			} else if (action == MotionEvent.ACTION_MOVE) {
				final float dx = (x - mX) / v.getWidth();
				final float dy = (y - mY) / v.getHeight();
				if (mMode == Mode.PAN) {
					mMapControl.pan(-dx, -dy);
				} else if (mMode == Mode.PINCHZOOM) {
					float newDist = spacing(event);
					if (newDist > 10f) {
						final float scale = newDist / oldDist;
						final float xx = mMidPoint.x / v.getWidth();
						final float yy = mMidPoint.y / v.getHeight();
						mMapControl.zoom(scale, xx, yy);
						oldDist = newDist;
					}
				} else {
					final float scrollX = mDownX - x;
					final float scrollY = mDownY - y;

					final float dist = (float) Math.sqrt(scrollX * scrollX
							+ scrollY * scrollY);

					if (dist >= mScaledTouchSlop) {
						mMode = Mode.PAN;
					}
				}
				mX = x;
				mY = y;
			} else if (action == MotionEvent.ACTION_POINTER_UP) {
				if (event.getPointerCount() > 1 && mMode == Mode.PINCHZOOM) {
					panAfterPinchTimeout = System.currentTimeMillis() + 100;
				}
				mMode = Mode.UNDEFINED;
			} else if (action == MotionEvent.ACTION_UP) {
				long upTime = System.currentTimeMillis();
				if (upTime - downTime < tapTimeOut) {
					isTap = true;
				} else {
					isTap = false;
				}
				// isTap
				if (isTap == true) {
					float touchX = mMapView.getBitmapLeft()
							+ ((mDownX - mMapView.getViewLeft()) / mMapView
									.getViewWidth())
							* mMapView.getBitmapWidth();
					float touchY = mMapView.getBitmapTop()
							+ ((mDownY - mMapView.getViewTop()) / mMapView
									.getViewHeight())
							* mMapView.getBitmapHeight();

					// Toast for which building touched
					Toast toast = Toast.makeText(getApplicationContext(), "",
							Toast.LENGTH_SHORT);
					toast.setText(findPlaceFromDB((int) touchX, (int) touchY));
					toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2,
							toast.getYOffset() / 2);
					toast.show();
					// --------display panel---------
					if (!"Info Not Available".equals(findPlaceFromDB(
							(int) touchX, (int) touchY)))
						showInfoOfPlace(findPlaceFromDB((int) touchX,
								(int) touchY));
				}
				if (mMode == Mode.PAN) {
					final long now = System.currentTimeMillis();
					if (panAfterPinchTimeout < now) {
						mVelocityTracker.computeCurrentVelocity(1000,
								mScaledMaximumFlingVelocity);
						mMapControl
								.startFling(
										-mVelocityTracker.getXVelocity()
												/ v.getWidth(),
										-mVelocityTracker.getYVelocity()
												/ v.getHeight());
					}
				} else if (mMode != Mode.PINCHZOOM) {
					mMapControl.startFling(0, 0);
				}
				mVelocityTracker.recycle();
				mVelocityTracker = null;
			}
		}

		return true;
	}

	/** Determine the space between the first two fingers */
	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	/** Calculate the mid point of the first two fingers */
	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}

	private void showInfoOfPlace(String place) {
		// TODO Auto-generated method stub
		// ---------------find info for place-----------

		DBAdapter adapter = new DBAdapter(getApplicationContext());
		try {
			adapter.getInstance(getApplicationContext());
			adapter.createDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		contents_info = adapter.searchPlaceForInfo(place);
		adapter.close();

		if (contents_info != null) {

			// -----display in panel--------
			View view = panel_map.getView();

			((TextView) view.findViewById(R.id.textTitle)).setText(place);

			for (int i = 0; i < 4; i++)
				iv[i].setVisibility(View.GONE);

			/*
			 * iv1.setImageResource(R.drawable.map021);
			 * iv2.setImageResource(R.drawable.map021);
			 * iv2.setImageResource(R.drawable.map022);
			 * iv3.setImageResource(R.drawable.map023);
			 * iv4.setImageResource(R.drawable.map024);
			 */

			int imageId = Integer.parseInt(contents_info[0]);
			imageId -= 1;
			for (int i = 0; i < images[imageId].length; i++) {
				iv[i].setImageResource(images[imageId][i]);
				iv[i].setVisibility(View.VISIBLE);
			}

			// get no. of images---contents_info[4] contains no. of images

			/*
			 * if (false) { TextView tv = new TextView(this);
			 * tv.setText("No Info Found"); tv.setPadding(10, 10, 0, 0);
			 * llScrollTelDirectory.addView(tv); } else {
			 * 
			 * for (int i = 0; i < 2; i++) { LinearLayout ll = new
			 * LinearLayout(this); ll.setOrientation(LinearLayout.HORIZONTAL);
			 * LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
			 * LinearLayout.LayoutParams.WRAP_CONTENT,
			 * LinearLayout.LayoutParams.WRAP_CONTENT); ImageView iv = new
			 * ImageView(this); //set parameters of images
			 * iv.setScaleType(ImageView.ScaleType.FIT_XY);
			 * iv.setImageResource(R.drawable.bgclock); ll.addView(iv);
			 * 
			 * ll.setPadding(5, 10, 0, 0); llScrollImages.addView(iv, lp); } }
			 */

			((TextView) view.findViewById(R.id.textDesc))
					.setText(contents_info[1]);

			panel_map.show();
			if ("".equals(contents_info[2]))
				bCallMap.setVisibility(View.GONE);
			else {
				bCallMap.setVisibility(View.VISIBLE);
				if (contents_info[2].length() <= 4)
					contents_info[2] = " 0133228" + contents_info[2];
				else
					contents_info[2] = " 01332" + contents_info[2];
			}

			if ("".equals(contents_info[3]))
				bMailMap.setVisibility(View.GONE);
			else
				bMailMap.setVisibility(View.VISIBLE);
		}

	}

	private String findPlaceFromDB(int touchX, int touchY) {
		// will look db for getting venue from x n y
		DBAdapter search = new DBAdapter(getApplicationContext());
		try {
			search.getInstance(getApplicationContext());
			search.createDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		venue = search.searchEntryForVenue("" + touchX, "" + touchY);

		if (venue == null)
			venue = "Info Not Available";

		search.close();
		return venue;
	}

	// ------------------add other argument advanced before calling
	// this----------
	private void showTelData(String place, String advanced) {
		// TODO Auto-generated method stub
		llScrollTelDirectory.setVisibility(View.GONE);
		llScrollTelDirectory.removeAllViews();

		// show details
		DBAdapter search = new DBAdapter(getApplicationContext());
		try {
			search.getInstance(getApplicationContext());
			search.createDataBase();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		venue = place;
		s = search.searchEntryForDetails(place, advanced);
		search.close();

		if (s.length == 0) {
			TextView tv = new TextView(this);
			tv.setText("No Info Found");
			tv.setPadding(10, 10, 0, 0);
			llScrollTelDirectory.addView(tv);
		} else {

			for (int i = 0; i < s.length; i++) {
				LinearLayout ll = new LinearLayout(this);
				ll.setOrientation(LinearLayout.VERTICAL);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.FILL_PARENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
				LinearLayout.LayoutParams lpText = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
				// 0
				TextView tv = new TextView(this);

				// ---More---
				if (!"".equals(s[i][3])) {
					tv = new TextView(this);

					tv.setTextSize(20);
					tv.setTextColor(Color.BLACK);
					tv.setTypeface(null, Typeface.BOLD_ITALIC);
					tv.setText(s[i][3]);
					ll.addView(tv);
				} else {
					tv.setTextSize(20);
					tv.setTextColor(Color.BLACK);
					tv.setTypeface(null, Typeface.BOLD_ITALIC);
					tv.setText(s[i][0]);
					ll.addView(tv);
				}

				// ---Number---
				tv = new TextView(this);

				tv.setTextSize(14);
				tv.setTextColor(Color.BLACK);
				tv.setLayoutParams(lpText);
				tv.setAutoLinkMask(Linkify.PHONE_NUMBERS);
				if (s[i][1].length() <= 4)
					s[i][1] = "0133-228" + s[i][1];
				else if (s[i][1].length() >= 10)
					s[i][1] = "0" + s[i][1];
				else
					s[i][1] = "0133-2" + s[i][1];
				tv.setText("Phone : " + s[i][1]);
				ll.addView(tv);

				// ---email---
				if (!"".equals(s[i][2])) {
					tv = new TextView(this);
					tv.setTextSize(14);
					tv.setLayoutParams(lpText);
					tv.setAutoLinkMask(Linkify.EMAIL_ADDRESSES);
					tv.setTextColor(Color.BLACK);
					tv.setText("E-mail : " + s[i][2] + "@iitr.ernet.in");
					ll.addView(tv);
				}

				ll.setPadding(5, 10, 0, 0);
				ll.setId(i);
				llScrollTelDirectory.addView(ll, lp);
				ll.setOnClickListener(this);
			}
		}

		llScrollTelDirectory.setVisibility(View.VISIBLE);
	}

	// ----------------later to be used for wona feeds-------------
	/*
	 * private void showUpcomingEvents() { // TODO Auto-generated method stub
	 * llScrollWona.setVisibility(View.GONE); llScrollWona.removeAllViews();
	 * 
	 * Time time = new Time();
	 * 
	 * time.setToNow(); int date = time.monthDay; int hours = time.hour; int
	 * minutes = time.minute;
	 * 
	 * // extract data DBAdapter upcoming = new
	 * DBAdapter(getApplicationContext()); try {
	 * upcoming.getInstance(getApplicationContext()); upcoming.createDataBase();
	 * } catch (SQLException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); } if (showTodayOnly) s =
	 * upcoming.searchEntryForTimeTodays(date, hours, minutes); else s =
	 * upcoming.searchEntryForTime(date, hours, minutes); upcoming.close();
	 * 
	 * if (s.length == 0) { TextView tv = new TextView(this);
	 * tv.setText("No Upcoming Events"); tv.setPadding(10, 10, 0, 0);
	 * llScrollWona.addView(tv); } else {
	 * 
	 * for (int i = 0; i < s.length; i++) { LinearLayout ll = new
	 * LinearLayout(this); ll.setOrientation(LinearLayout.VERTICAL);
	 * LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
	 * LinearLayout.LayoutParams.FILL_PARENT,
	 * LinearLayout.LayoutParams.WRAP_CONTENT); // 0 TextView tv = new
	 * TextView(this);
	 * 
	 * tv.setTypeface(Typeface.createFromAsset(getApplicationContext( )
	 * .getAssets(), "TEMPSITC.TTF"));
	 * 
	 * tv.setTextSize(20); tv.setTextColor(Color.BLACK); tv.setTypeface(null,
	 * Typeface.BOLD_ITALIC); tv.setText(s[i][0]); ll.addView(tv); // 1 tv = new
	 * TextView(this);
	 * 
	 * tv.setTypeface(Typeface.createFromAsset(getApplicationContext( )
	 * .getAssets(), "PAPYRUS.TTF"));
	 * 
	 * tv.setTextSize(16); tv.setTextColor(Color.rgb(201, 250, 15));
	 * tv.setTextColor(Color.BLACK); tv.setText(s[i][1]); ll.addView(tv); // 2
	 * tv = new TextView(this);
	 * 
	 * tv.setTypeface(Typeface.createFromAsset(getApplicationContext( )
	 * .getAssets(), "BRADHITC.TTF"));
	 * 
	 * tv.setTextSize(14); tv.setTextColor(Color.rgb(250, 255, 149));
	 * tv.setTextColor(Color.BLACK); tv.setText(s[i][2]); ll.addView(tv); // 3
	 * tv = new TextView(this);
	 * 
	 * tv.setTypeface(Typeface.createFromAsset(getApplicationContext( )
	 * .getAssets(), "BRADHITC.TTF"));
	 * 
	 * tv.setTextSize(14); tv.setTextColor(Color.rgb(125, 252, 249));
	 * tv.setTextColor(Color.BLACK); tv.setText(s[i][3]); ll.addView(tv);
	 * 
	 * ll.setPadding(5, 10, 0, 0); ll.setId(i); llScrollWona.addView(ll, lp);
	 * ll.setOnClickListener(this); } }
	 * 
	 * llScrollWona.setVisibility(View.VISIBLE); }
	 */

	@Override
	protected void onDestroy() {
		super.onDestroy();

		mBitmap.recycle();
		mMapView.setOnTouchListener(null);
		mMapControl.getZoomState().deleteObservers();
	}

	// //---------Class PopupPanel---------
	private class PopupPanel {
		View popup;
		boolean isVisible = false;

		PopupPanel(int layout) {
			ViewGroup parent = (ViewGroup) llParent.getParent();

			popup = getLayoutInflater().inflate(layout, parent, false);
		}

		View getView() {
			return (popup);
		}

		void show() {

			hide();

			((ViewGroup) llParent.getParent()).addView(popup);
			isVisible = true;
		}

		void hide() {
			if (isVisible) {
				isVisible = false;
				((ViewGroup) popup.getParent()).removeView(popup);
			}
		}
	}

	private class DownloadEventsXml extends AsyncTask<Void, Void, Void> {
		private boolean ntwkConStatus = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pdia = ProgressDialog.show(TabPage.this, "", "Loading...", true,
					true, new OnCancelListener() {
						public void onCancel(DialogInterface dialog) {
							dis.cancel(true);
						}
					});
			/* pdia.setCancelable(true); */
		}

		@Override
		protected Void doInBackground(final Void... inf) {
			ShikharParser dwnlXml = new ShikharParser();
			CheckNtwkCon NtwkCon = new CheckNtwkCon();
			if (NtwkCon.CheckInternet(TabPage.this)) {
				ntwkConStatus = true;
				dwnlXml.parseShikharXML(TabPage.this);
			}
			return null;
		}

		protected void onPostExecute(final Void result) {
			pdia.dismiss();
			String text;
			if (ntwkConStatus)
				text = "Details updated successfully!";
			else
				text = "Please check your network connection";
			Toast msg = Toast.makeText(TabPage.this, text, Toast.LENGTH_LONG);
			msg.show();
			dis.cancel(true);
			dis = null;
			/*
			 * started=false; pdiStarted=false;
			 */
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (dis != null)
			dis.cancel(true);
		if (null != pdia)
			pdia.dismiss();

	}

	@Override
	protected void onStop() {
		super.onStop();
		if (dis != null)
			dis.cancel(true);
		if (null != pdia)
			pdia.dismiss();

	}
}
