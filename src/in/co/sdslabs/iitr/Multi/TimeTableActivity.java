package in.co.sdslabs.iitr.Multi;

import java.util.Calendar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class TimeTableActivity extends SherlockFragmentActivity implements
		ActionBar.TabListener, OnClickListener {
	SharedPreferences somedata;
	public static String filename = "MySharedString";

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	TextView[] tv, tv11;

	// textView = new TextView[count];

	String name1 = null;
	private static final int REQUEST_CODE = 0;
	private static final int REQUEST_CODED = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int count = 50;

		tv = new TextView[count];
		// tv11 = new TextView[count];
		// new loadSomeStuff().execute();
		// Toast.makeText(TimeTableActivity.this, "came", Toast.LENGTH_LONG).show();

		/*
		 * Thread timer = new Thread() { public void run() { String Subjetcs[] =
		 * { "IE", "CE-252", "CE-242", "CE-212", "CE-222", "ES-201", "BM-201",
		 * "" }; String Snotes[] = { "Type Here...", "Type Here...",
		 * "Type Here...", "Type Here...", "Type Here...", "Type Here...",
		 * "Type Here...", "" };
		 * 
		 * String Sattend[] = { "", "", "", "", "", "", "", "" }; Notes note =
		 * new Notes(TimeTableActivity.this); note = new Notes(TimeTableActivity.this);
		 * try { note.open(); } catch (Exception e) {
		 * 
		 * 
		 * e.printStackTrace(); }
		 * 
		 * 
		 * tv = new TextView[count1]; tv11 = new TextView[count1]; String name1
		 * = null;
		 * 
	7	 * 
		 * for (int i = 0; i < 8; i++) { String name = Subjetcs[i]; String
		 * hotness = Snotes[i]; String attend = Sattend[i];
		 * note.createEntry(name, hotness,attend); } note.close(); String info[]
		 * = { "LIE BGSEC", "", "LIE BGSEC", "", "LIE BGSEC",
		 * 
		 * "LCE-252 A  VP Newton1", "LES-201 Newton1", "LCE-252 A VP Newton1",
		 * "LCE-242 A KSHP Newton1", "LCE-252 A VP Newton1",
		 * 
		 * "LCE-222 A PKG-I Newton1", "LCE-222 A PKG-I Newton1",
		 * "LCE-222 A PKG-I Newton1", "PCE-222 C1 MA GEOM LAB",
		 * "LCE-212 A PK-I Newton1",
		 * 
		 * "LCE-242 A KSHP Newton1", "LCE-242 A KSHP Newton1",
		 * "LCE-212 A PK-I Newton1", "PCE-222 C1 MA GEOM LAB",
		 * "LBM-201 Newton1",
		 * 
		 * "TBM-201 C1 Newton1", "LCE-252 C1 VP Newton1", "LES-201 Newton1",
		 * "PCE-222 C1 MA GEOM LAB", "LBM-201 Newton1",
		 * 
		 * "", "PCE-212 C1 AAK ENV LAB", "", "PCE-252 A ESA LAB", "",
		 * 
		 * "", "PCE-212 C1 AAK ENV LAB", "TCE-212 C1 PK-I Newton1",
		 * "PCE-252 C1 AU ESA LAB", "",
		 * 
		 * "TCE-242 A AG Newton1", "", "", "", "",
		 * 
		 * "", "", "", "", "",
		 * 
		 * "", "", "", "", ""
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }; String otherInfo[] = { "Same as You", "No class", "Same as You",
		 * "No class", "Same as You",
		 * 
		 * "LCE-252 B PB-I Newton2", "Same as You", "LCE-252 B PB-I Newton2",
		 * "LCE-242 B DK Newton2", "LCE-252 B PB-I Newton2",
		 * 
		 * "LCE-222 A JKG Newton2", "LCE-222 A JKG Newton2",
		 * "LCE-222 A JKG Newton2",
		 * "TCE-242 C2 DK Newton1\nTCE-212 C3 PK-III 302\nPCE-252 C5/C6SC-II ESA\nPCE-242 C6/C5 AG Hdy lab"
		 * , "LCE-212 A PK-III Newton2",
		 * 
		 * "LCE-242 B DK Newton2", "LCE-242 B DK Newton2",
		 * "LCE-212 A PK-III Newton2",
		 * "PCE-252 C5/C6 SC-II ESA Lab\nPCE-242 C6/C5 AG Hyd Lab",
		 * "LCE-252 B VP Newton2",
		 * 
		 * "Same as You",
		 * "TBM-201 C2 Newton 2\nTCE-242 C3 PKS 302\nTCE-242 C4 KSHP 340\nTCE-252 C5 SC-II 305\nTCE-252 C6 AU 308"
		 * ,
		 * "TCE-252 C2 SC-II Newton2\nTBM-201 C3 302\nTBM-201 C4 340\nTCE-242 C5 AG 305\nTCE-242 C6 PKS 308"
		 * , "Same As You", "TBM-201 C5 305\nTBM-201 C6 308",
		 * 
		 * "Same As You", "PCE-222 C6 RDG Geom Lab",
		 * "PCE-222 C2 PKG-I Geom Lab", "PCE-212 C4 IM Env Lab",
		 * "PCE-242 C2 DK Hyd Lab\nPCE-222 C4 MA Geom Lab",
		 * 
		 * "PCE-222 C5 JKG Geom Lab",
		 * "PCE-212 C2 IM Env Lab\nPCE-252 C3/C4 NMB ESA Lab\nPCE-242 C4/C3 KSHP Hyd Lab\nPCE-242 C6 RDG Geom Lab"
		 * , "PCE-222 C2 PKG-I Geom Lab\nTCE-252 C3 VP 302",
		 * "PCE-222 C3 PKG-I Geom Lab\nPCE-212 Env Lab\nTCE-212 C6 PK-III 308",
		 * "PCE-242 C2 DK Hyd Lab\nPCE-222 C4 MA Geom Lab",
		 * 
		 * "PCE-222 C5 JKG Geom Lab",
		 * "PCE-212 C2 IM Env Lab\nPCE-252 C3/C4 MB ESA Lab\nPCE-242 C4/C3 KSHP Hyd Lab\nTCE-212 C5 PK-I 305\nPCE-222 C6 RDG Geom Lab"
		 * , "PCE-222 C3 PKG-I Geom Lab\nPCE-212 C5 BRG Evv Lab",
		 * "PCE-222 C4 MA Geom Lab\nPCE-212 C6 AAK Env Lab",
		 * "TCE-212 C2 PK-III Newton1\nPCE-222 C5 JKG Geom Lab",
		 * 
		 * "T/P-IE (A1)", "PCE-212 C3 AAK Env Lab", "PCE-212 C5 BRG  Env Lab",
		 * "PCE-212 C6 AAK Env Lab", "T/P-IE (A2)",
		 * 
		 * "T/P-IE (A1)", "", "", "", "T/P-IE (A2)"
		 * 
		 * 
		 * 
		 * }; String color[] = { "#CDFFFF", "#F9B7FF", "#CDFFFF", "#F9B7FF",
		 * "#CDFFFF", "#CDFFFF", "#F9B7FF" }; HotOrNot entry; entry = new
		 * HotOrNot(TimeTableActivity.this); try { entry.open(); } catch (Exception
		 * e) { e.printStackTrace(); }
		 * 
		 * for (int i1 = 0; i1 < 50; i1++) { String name = info[i1]; String
		 * hotness = otherInfo[i1]; entry.createEntry(name, hotness); }
		 * 
		 * entry.close();
		 * 
		 * 
		 * } };
		 */

		if (getResources().getConfiguration().orientation == 1) {
			setContentView(R.layout.swipetabpager);

			// Set up the action bar.
			final ActionBar actionBar = getSupportActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

			// Create the adapter that will return a fragment for each of the
			// three
			// primary sections of the app.
			mSectionsPagerAdapter = new SectionsPagerAdapter(
					getSupportFragmentManager());

			// Set up the ViewPager with the sections adapter.
			mViewPager = (ViewPager) findViewById(R.id.pager);
			mViewPager.setAdapter(mSectionsPagerAdapter);

			// When swiping between different sections, select the corresponding
			// tab. We can also use ActionBar.Tab#select() to do this if we have
			// a reference to the Tab.
			mViewPager
					.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
						@Override
						public void onPageSelected(int position) {
							actionBar.setSelectedNavigationItem(position);
						}
					});

			// For each of the sections in the app, add a tab to the action bar.
			for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
				// Create a tab with text corresponding to the page title
				// defined by
				// the adapter. Also specify this Activity object, which
				// implements
				// the TabListener interface, as the callback (listener) for
				// when
				// this tab is selected.
				actionBar.addTab(actionBar.newTab()
						.setText(mSectionsPagerAdapter.getPageTitle(i))
						.setTabListener(this));
			}
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_WEEK);
			if (day == 3) {
				mViewPager.setCurrentItem(1);
			} else if (day == 4) {
				mViewPager.setCurrentItem(2);
			} else if (day == 5) {
				mViewPager.setCurrentItem(3);
			} else if (day == 6) {
				mViewPager.setCurrentItem(4);
			} else {
				mViewPager.setCurrentItem(0);
			}
		} else {
			setContentView(R.layout.frag2);
			tv[0] = (TextView) findViewById(R.id.tv1);
			tv[1] = (TextView) findViewById(R.id.tv2);
			tv[2] = (TextView) findViewById(R.id.tv3);
			tv[3] = (TextView) findViewById(R.id.tv4);
			tv[4] = (TextView) findViewById(R.id.tv5);
			tv[5] = (TextView) findViewById(R.id.tv6);
			tv[6] = (TextView) findViewById(R.id.tv7);
			tv[7] = (TextView) findViewById(R.id.tv8);
			tv[8] = (TextView) findViewById(R.id.tv9);
			tv[9] = (TextView) findViewById(R.id.tv10);
			tv[10] = (TextView) findViewById(R.id.tv11);
			tv[11] = (TextView) findViewById(R.id.tv12);
			tv[12] = (TextView) findViewById(R.id.tv13);
			tv[13] = (TextView) findViewById(R.id.tv14);
			tv[14] = (TextView) findViewById(R.id.tv15);
			tv[15] = (TextView) findViewById(R.id.tv16);
			tv[16] = (TextView) findViewById(R.id.tv17);
			tv[17] = (TextView) findViewById(R.id.tv18);
			tv[18] = (TextView) findViewById(R.id.tv19);
			tv[19] = (TextView) findViewById(R.id.tv20);
			tv[20] = (TextView) findViewById(R.id.tv21);
			tv[21] = (TextView) findViewById(R.id.tv22);
			tv[22] = (TextView) findViewById(R.id.tv23);
			tv[23] = (TextView) findViewById(R.id.tv24);
			tv[24] = (TextView) findViewById(R.id.tv25);
			tv[25] = (TextView) findViewById(R.id.tv26);
			tv[26] = (TextView) findViewById(R.id.tv27);
			tv[27] = (TextView) findViewById(R.id.tv28);
			tv[28] = (TextView) findViewById(R.id.tv29);
			tv[29] = (TextView) findViewById(R.id.tv30);
			tv[30] = (TextView) findViewById(R.id.tv31);
			tv[31] = (TextView) findViewById(R.id.tv32);
			tv[32] = (TextView) findViewById(R.id.tv33);
			tv[33] = (TextView) findViewById(R.id.tv34);
			tv[34] = (TextView) findViewById(R.id.tv35);
			tv[35] = (TextView) findViewById(R.id.tv36);
			tv[36] = (TextView) findViewById(R.id.tv37);
			tv[37] = (TextView) findViewById(R.id.tv38);
			tv[38] = (TextView) findViewById(R.id.tv39);
			tv[39] = (TextView) findViewById(R.id.tv40);
			tv[40] = (TextView) findViewById(R.id.tv41);
			tv[41] = (TextView) findViewById(R.id.tv42);
			tv[42] = (TextView) findViewById(R.id.tv43);
			tv[43] = (TextView) findViewById(R.id.tv44);
			tv[44] = (TextView) findViewById(R.id.tv45);
			tv[45] = (TextView) findViewById(R.id.tv46);
			tv[46] = (TextView) findViewById(R.id.tv47);
			tv[47] = (TextView) findViewById(R.id.tv48);
			tv[48] = (TextView) findViewById(R.id.tv49);
			tv[49] = (TextView) findViewById(R.id.tv50);

			/*
			 * for (int i = 0; i < 50; i++) { int i1 = i + 1; name1 =
			 * entry1.getName(i1); tv[i].setText(name1);
			 * 
			 * }
			 */
			/*
			 * LoadSomeStuff loadSomeStuff = new LoadSomeStuff(); String s;
			 */
			// new loadSomeStuff().execute("");
			HotOrNot entry1;
			entry1 = new HotOrNot(TimeTableActivity.this);
			try {
				entry1.open();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (int i = 0; i < 50; i++) {
				int i1 = i + 1;
				name1 = entry1.getName(i1);   //issue of repeated database hitting
				if (name1 != null) {
					// System.out.println("name: " + name1);
					tv[i].setText(name1);
				}

			}
			entry1.close();

			for (int i = 0; i < 50; i++) {
				tv[i].setOnClickListener(this);
			}

		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (getResources().getConfiguration().orientation != 1) {
			for (int i = 0; i < 50; i++) {
				// tv[i]
				tv[i].setEnabled(true);

			}
			HotOrNot entry1;
			entry1 = new HotOrNot(TimeTableActivity.this);
			try {
				entry1.open();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (int i = 0; i < 50; i++) {
				int i1 = i + 1;
				name1 = entry1.getName(i1);
				if (name1 != null) {
					// System.out.println("name: " + name1);
					tv[i].setText(name1);
				}

			}
			entry1.close();

			for (int i = 0; i < 50; i++) {
				tv[i].setOnClickListener(this);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.notes1) {
			Intent intent1 = new Intent(this,Seenote.class);
			startActivity(intent1);
		} else if (item.getItemId() == R.id.resetTimeTable) {
			Toast.makeText(this, "Now you can reset TimeTable",
					Toast.LENGTH_LONG).show();
			somedata = getSharedPreferences(filename, 0);
			SharedPreferences.Editor editor = somedata.edit();
			editor.putBoolean("flag", false);
			editor.commit();
			Notes note;
			note = new Notes(this);
			try {
				note.open();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			note.drop();
			note.close();
			HotOrNot entry;
			entry = new HotOrNot(TimeTableActivity.this);
			try {
				entry.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
			entry.drop();
			entry.close();
			Intent openStartingPoint = new Intent(this,Own.class);
			startActivity(openStartingPoint);
			finish();
		} else if (item.getItemId() == R.id.attend) {
			Intent i2 = new Intent(this,Attendance.class);
			startActivity(i2);
		} else if (item.getItemId() == R.id.addSubject) {
			Intent i2 = new Intent(this,Own.class);
			startActivity(i2);
		}
		return false;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 5;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			if (position == 0) {
				return getString(R.string.title_section1);
			} else if (position == 1) {
				return getString(R.string.title_section2);
			} else if (position == 2) {
				return getString(R.string.title_section3);
			} else if (position == 3) {
				return getString(R.string.title_section4);
			} else if (position == 4) {
				return getString(R.string.title_section5);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment implements
			OnClickListener {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		int a;
		TextView[] textView;

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			int count = 10;
			textView = new TextView[count];

			View view = inflater.inflate(R.layout.port, container, false);
			textView[0] = (TextView) view.findViewById(R.id.tv111);
			textView[1] = (TextView) view.findViewById(R.id.tv112);
			textView[2] = (TextView) view.findViewById(R.id.tv113);
			textView[3] = (TextView) view.findViewById(R.id.tv114);
			textView[4] = (TextView) view.findViewById(R.id.tv115);
			textView[5] = (TextView) view.findViewById(R.id.tv116);
			textView[6] = (TextView) view.findViewById(R.id.tv117);
			textView[7] = (TextView) view.findViewById(R.id.tv118);
			textView[8] = (TextView) view.findViewById(R.id.tv119);
			textView[9] = (TextView) view.findViewById(R.id.tv1110);

			/*
			 * textView[0].setText(Integer.toString(getArguments().getInt(
			 * ARG_SECTION_NUMBER)));
			 * textView[1].setText(Integer.toString(getArguments().getInt(
			 * ARG_SECTION_NUMBER)));
			 * textView[2].setText(Integer.toString(getArguments().getInt(
			 * ARG_SECTION_NUMBER)));
			 */

			a = (getArguments().getInt(ARG_SECTION_NUMBER));
			// new loadSomeStuffs().execute(a);
			HotOrNot entry;
			entry = new HotOrNot(getActivity());
			try {
				entry.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int l;
			Notes note = new Notes(getActivity());
			try {
				note.open();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int k = note.number();
			String temp = "";
			if (a == 1) {
				int i1 = 1;
				for (int i = 0; i < 10; i++) {
					
					temp = entry.getName(i1);
			/*		if(!temp.equals(""))
					{
					for (int j = 1; j <=k ; j++) {
					
						if(temp.contains(note.getName(j)))
						{
							String a = note.getName(j);
							Toast.makeText(getActivity(),i+"came"+note.getName(j) ,Toast.LENGTH_SHORT).show();
							
							temp = temp.replace(note.getName(j),note.getDesc(j));
							Toast.makeText(getActivity(), i+temp, Toast.LENGTH_SHORT).show();
							break;
						}
					}
					}*/
					//temp = temp.replace(note.getName(), newChar)
					textView[i].setText(temp);   //issue of repeated database hitting 
					i1 = i1 + 5;
				}
			} else if (a == 2) {
				int i1 = 2;
				for (int i = 0; i < 10; i++) {

					textView[i].setText(entry.getName(i1));   //issue of repeated database hitting
					i1 = i1 + 5;
				}
			} else if (a == 3) {
				int i1 = 3;
				for (int i = 0; i < 10; i++) {

					textView[i].setText(entry.getName(i1));  //issue of repeated database hitting
					i1 = i1 + 5;
				}
			} else if (a == 4) {
				int i1 = 4;
				for (int i = 0; i < 10; i++) {
					textView[i].setText(entry.getName(i1));  //issue of repeated database hitting

					i1 = i1 + 5;
				}
			} else if (a == 5) {
				int i1 = 5;
				for (int i = 0; i < 10; i++) {
					textView[i].setText(entry.getName(i1));   //issue of repeated database hitting
					i1 = i1 + 5;

				}
			}

			// for(int i=0;i<3;i++)
			note.close();
			entry.close();

			for (int i1 = 0; i1 < 10; i1++) {
				textView[i1].setOnClickListener(this);  //issue of repeated database hitting
			}
			return view;

		}

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			try {
				for (int i = 0; i < 10; i++) {
					textView[i].setEnabled(true);

				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			// textView[].setEnabled(true);
			a = (getArguments().getInt(ARG_SECTION_NUMBER));
			// new loadSomeStuffs().execute(a);
			HotOrNot entry;
			entry = new HotOrNot(getActivity());
			try {
				entry.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Notes note = new Notes(getActivity());
			try {
				note.open();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int k = note.number();
			String temp = "";
			if (a == 1) {
				int i1 = 1;
				for (int i = 0; i < 10; i++) {
					
					temp = entry.getName(i1);
					/*if(!temp.equals(""))
					{
					for (int j = 1; j <=k ; j++) {
					
						if(temp.contains(note.getName(j)))
						{
							String a = note.getName(j);
							Toast.makeText(getActivity(),i+"came"+note.getName(j) ,Toast.LENGTH_SHORT).show();
							
							temp = temp.replace(note.getName(j),note.getDesc(j));
							Toast.makeText(getActivity(), i+temp, Toast.LENGTH_SHORT).show();
							break;
						}
					}
					}*/
					//temp = temp.replace(note.getName(), newChar)
					textView[i].setText(temp);   //issue of repeated database hitting 
					i1 = i1 + 5;
				}
			} else if (a == 2) {
				int i1 = 2;
				for (int i = 0; i < 10; i++) {

					textView[i].setText(entry.getName(i1));
					i1 = i1 + 5;
				}
			} else if (a == 3) {
				int i1 = 3;
				for (int i = 0; i < 10; i++) {

					textView[i].setText(entry.getName(i1));
					i1 = i1 + 5;
				}
			} else if (a == 4) {
				int i1 = 4;
				for (int i = 0; i < 10; i++) {
					textView[i].setText(entry.getName(i1));

					i1 = i1 + 5;
				}
			} else if (a == 5) {
				int i1 = 5;
				for (int i = 0; i < 10; i++) {
					textView[i].setText(entry.getName(i1));
					i1 = i1 + 5;

				}
			}

			// for(int i=0;i<3;i++)

			entry.close();

			for (int i1 = 0; i1 < 10; i1++) {
				textView[i1].setOnClickListener(this);
			}

		}

		public class loadSomeStuffs extends AsyncTask<Integer, Void, String> {

			ProgressDialog dialog;

			@Override
			protected void onPreExecute() {
				dialog = new ProgressDialog(getActivity());
				dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				dialog.setMessage("Please wait");
				dialog.show();
			}

			@Override
			protected String doInBackground(Integer... params) {
				// TODO Auto-generated method stub
				// Toast.makeText(getActivity(), "apple",
				// Toast.LENGTH_LONG).show();
				HotOrNot entry;
				entry = new HotOrNot(getActivity());
				try {
					entry.open();
				} catch (Exception e) {
					e.printStackTrace();
				}

				Integer integer = params[0];
				if (integer == 1) {
					int i1 = 1;
					for (int i = 0; i < 10; i++) {
						textView[i].setText(entry.getName(i1));
						i1 = i1 + 5;
					}
				} else if (integer == 2) {
					int i1 = 2;
					for (int i = 0; i < 10; i++) {

						textView[i].setText(entry.getName(i1));
						i1 = i1 + 5;
					}
				} else if (integer == 3) {
					int i1 = 3;
					for (int i = 0; i < 10; i++) {

						textView[i].setText(entry.getName(i1));
						i1 = i1 + 5;
					}
				} else if (integer == 4) {
					int i1 = 4;
					for (int i = 0; i < 10; i++) {
						textView[i].setText(entry.getName(i1));

						i1 = i1 + 5;
					}
				} else if (integer == 5) {
					int i1 = 5;
					for (int i = 0; i < 10; i++) {
						textView[i].setText(entry.getName(i1));
						i1 = i1 + 5;

					}
				}

				// for(int i=0;i<3;i++)

				entry.close();
				// dialog.dismiss();

				return "";
			}

			@Override
			protected void onPostExecute(String str) {
				dialog.dismiss();

			}
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Bundle basket = new Bundle();
			String bread = null, apples = null, almonds = null;
			HotOrNot entry = new HotOrNot(getActivity());
			try {
				entry.open();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int b = (a - 1) + 5;
			if (v.getId() == R.id.tv111) {
				bread = entry.getName(a);
				apples = entry.getHotness(a);
				almonds = entry.getID(a);
			} else if (v.getId() == R.id.tv112) {
				bread = entry.getName(b + 1);
				apples = entry.getHotness(b + 1);
				almonds = entry.getID(b + 1);
			} else if (v.getId() == R.id.tv113) {
				bread = entry.getName(b + 6);
				apples = entry.getHotness(b + 6);
				almonds = entry.getID(b + 6);
			} else if (v.getId() == R.id.tv114) {
				bread = entry.getName(b + 11);
				apples = entry.getHotness(b + 11);
				almonds = entry.getID(b + 11);
			} else if (v.getId() == R.id.tv115) {
				bread = entry.getName(b + 16);
				apples = entry.getHotness(b + 16);
				almonds = entry.getID(b + 16);
			} else if (v.getId() == R.id.tv116) {
				bread = entry.getName(b + 21);
				apples = entry.getHotness(b + 21);
				almonds = entry.getID(b + 21);
			} else if (v.getId() == R.id.tv117) {
				bread = entry.getName(b + 26);
				apples = entry.getHotness(b + 26);
				almonds = entry.getID(b + 26);
			} else if (v.getId() == R.id.tv118) {
				bread = entry.getName(b + 31);
				apples = entry.getHotness(b + 31);
				almonds = entry.getID(b + 31);
			} else if (v.getId() == R.id.tv119) {
				bread = entry.getName(b + 36);
				apples = entry.getHotness(b + 36);
				almonds = entry.getID(b + 36);
			} else if (v.getId() == R.id.tv1110) {
				bread = entry.getName(b + 41);
				apples = entry.getHotness(b + 41);
				almonds = entry.getID(b + 41);
			}
			basket.putString("key", bread);
			basket.putString("desc", apples);
			basket.putString("id", almonds);
			for (int i = 0; i < 10; i++) {
				textView[i].setEnabled(false);
			}
			Intent openStartingPoint = new Intent(getActivity(),Dummy.class);
			openStartingPoint.putExtras(basket);

			startActivity(openStartingPoint);
			// startActivityForResult(openStartingPoint, REQUEST_CODE);

			entry.close();
		}

		/*
		 * @Override public void onActivityResult(int requestCode, int
		 * resultCode, Intent data) { if (resultCode == RESULT_OK && requestCode
		 * == REQUEST_CODED) { if (data.hasExtra("returnKey1")) { int b =
		 * data.getExtras().getInt("returnKey2"); String gotcheery =
		 * data.getExtras().getString("returnKey1"); HotOrNot entry = new
		 * HotOrNot(getActivity()); Toast.makeText(getActivity(),
		 * ""+gotcheery+b, Toast.LENGTH_LONG).show(); try { entry.open(); }
		 * catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } entry.updateEntry(b, gotcheery,
		 * entry.getHotness(b)); entry.close();
		 * 
		 * Toast.makeText(this,
		 * "change will be seen when you again open time table",
		 * Toast.LENGTH_LONG).show();
		 * 
		 * } else{ Toast.makeText(getActivity(), "else",
		 * Toast.LENGTH_LONG).show();
		 * 
		 * } } }
		 */

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Bundle basket = new Bundle();
		String bread = null, apples = null, almonds = null;
		HotOrNot entry = new HotOrNot(this);
		try {
			entry.open();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (v.getId() == R.id.tv1) {
			bread = entry.getName(1);
			apples = entry.getHotness(1);
			almonds = entry.getID(1);
		} else if (v.getId() == R.id.tv2) {
			bread = entry.getName(2);
			apples = entry.getHotness(2);
			almonds = entry.getID(2);
		} else if (v.getId() == R.id.tv3) {
			bread = entry.getName(3);
			apples = entry.getHotness(3);
			almonds = entry.getID(3);
		} else if (v.getId() == R.id.tv4) {
			bread = entry.getName(4);
			apples = entry.getHotness(4);
			almonds = entry.getID(4);
		} else if (v.getId() == R.id.tv5) {
			bread = entry.getName(5);
			apples = entry.getHotness(5);
			almonds = entry.getID(5);
		} else if (v.getId() == R.id.tv6) {
			bread = entry.getName(6);
			apples = entry.getHotness(6);
			almonds = entry.getID(6);
		} else if (v.getId() == R.id.tv7) {
			bread = entry.getName(7);
			apples = entry.getHotness(7);
			almonds = entry.getID(7);
		} else if (v.getId() == R.id.tv8) {
			bread = entry.getName(8);
			apples = entry.getHotness(8);
			almonds = entry.getID(8);
		} else if (v.getId() == R.id.tv9) {
			bread = entry.getName(9);
			apples = entry.getHotness(9);
			almonds = entry.getID(9);
		} else if (v.getId() == R.id.tv10) {
			bread = entry.getName(10);
			apples = entry.getHotness(10);
			almonds = entry.getID(10);
		} else if (v.getId() == R.id.tv11) {
			bread = entry.getName(11);
			apples = entry.getHotness(11);
			almonds = entry.getID(11);
		} else if (v.getId() == R.id.tv12) {
			bread = entry.getName(12);
			apples = entry.getHotness(12);
			almonds = entry.getID(12);
		} else if (v.getId() == R.id.tv13) {
			bread = entry.getName(13);
			apples = entry.getHotness(13);
			almonds = entry.getID(31);
		} else if (v.getId() == R.id.tv14) {
			bread = entry.getName(14);
			apples = entry.getHotness(14);
			almonds = entry.getID(14);
		} else if (v.getId() == R.id.tv15) {
			bread = entry.getName(15);
			apples = entry.getHotness(15);
			almonds = entry.getID(15);
		} else if (v.getId() == R.id.tv16) {
			bread = entry.getName(16);
			apples = entry.getHotness(16);
			almonds = entry.getID(16);
		} else if (v.getId() == R.id.tv17) {
			bread = entry.getName(17);
			apples = entry.getHotness(17);
			almonds = entry.getID(17);
		} else if (v.getId() == R.id.tv18) {
			try {
				bread = entry.getName(18);
				apples = entry.getHotness(18);
				almonds = entry.getID(18);

			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(this, "exception--" + e.getMessage(),
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		} else if (v.getId() == R.id.tv19) {
			bread = entry.getName(19);
			apples = entry.getHotness(19);
			almonds = entry.getID(19);
		} else if (v.getId() == R.id.tv20) {
			bread = entry.getName(20);
			apples = entry.getHotness(20);
			almonds = entry.getID(20);
		} else if (v.getId() == R.id.tv21) {
			bread = entry.getName(21);
			apples = entry.getHotness(21);
			almonds = entry.getID(21);
		} else if (v.getId() == R.id.tv22) {
			bread = entry.getName(22);
			apples = entry.getHotness(22);
			almonds = entry.getID(22);
		} else if (v.getId() == R.id.tv23) {
			bread = entry.getName(23);
			apples = entry.getHotness(23);
			almonds = entry.getID(23);
		} else if (v.getId() == R.id.tv24) {
			bread = entry.getName(24);
			apples = entry.getHotness(24);
			almonds = entry.getID(24);
		} else if (v.getId() == R.id.tv25) {
			bread = entry.getName(25);
			apples = entry.getHotness(25);
			almonds = entry.getID(25);
		} else if (v.getId() == R.id.tv26) {
			bread = entry.getName(26);
			apples = entry.getHotness(26);
			almonds = entry.getID(26);
		} else if (v.getId() == R.id.tv27) {
			bread = entry.getName(27);
			apples = entry.getHotness(27);
			almonds = entry.getID(27);
		} else if (v.getId() == R.id.tv28) {
			bread = entry.getName(28);
			apples = entry.getHotness(28);
			almonds = entry.getID(28);
		} else if (v.getId() == R.id.tv29) {
			bread = entry.getName(29);
			apples = entry.getHotness(29);
			almonds = entry.getID(29);
		} else if (v.getId() == R.id.tv30) {
			bread = entry.getName(30);
			apples = entry.getHotness(30);
			almonds = entry.getID(30);
		} else if (v.getId() == R.id.tv31) {
			bread = entry.getName(31);
			apples = entry.getHotness(31);
			almonds = entry.getID(31);
		} else if (v.getId() == R.id.tv32) {
			bread = entry.getName(32);
			apples = entry.getHotness(32);
			almonds = entry.getID(32);
		} else if (v.getId() == R.id.tv33) {
			bread = entry.getName(33);
			apples = entry.getHotness(33);
			almonds = entry.getID(33);
		} else if (v.getId() == R.id.tv34) {
			bread = entry.getName(34);
			apples = entry.getHotness(34);
			almonds = entry.getID(34);
		} else if (v.getId() == R.id.tv35) {
			bread = entry.getName(35);
			apples = entry.getHotness(35);
			almonds = entry.getID(35);
		} else if (v.getId() == R.id.tv36) {
			bread = entry.getName(36);
			apples = entry.getHotness(36);
			almonds = entry.getID(36);
		} else if (v.getId() == R.id.tv37) {
			bread = entry.getName(37);
			apples = entry.getHotness(37);
			almonds = entry.getID(37);
		} else if (v.getId() == R.id.tv38) {
			bread = entry.getName(38);
			apples = entry.getHotness(38);
			almonds = entry.getID(38);
		} else if (v.getId() == R.id.tv39) {
			bread = entry.getName(39);
			apples = entry.getHotness(39);
			almonds = entry.getID(39);
		} else if (v.getId() == R.id.tv40) {
			bread = entry.getName(40);
			apples = entry.getHotness(40);
			almonds = entry.getID(40);
		} else if (v.getId() == R.id.tv41) {
			bread = entry.getName(41);
			apples = entry.getHotness(41);
			almonds = entry.getID(41);
		} else if (v.getId() == R.id.tv42) {
			bread = entry.getName(42);
			apples = entry.getHotness(42);
			almonds = entry.getID(42);
		} else if (v.getId() == R.id.tv43) {
			bread = entry.getName(43);
			apples = entry.getHotness(43);
			almonds = entry.getID(43);
		} else if (v.getId() == R.id.tv44) {
			bread = entry.getName(44);
			apples = entry.getHotness(44);
			almonds = entry.getID(44);
		} else if (v.getId() == R.id.tv45) {
			bread = entry.getName(45);
			apples = entry.getHotness(45);
			almonds = entry.getID(45);
		} else if (v.getId() == R.id.tv46) {
			bread = entry.getName(46);
			apples = entry.getHotness(46);
			almonds = entry.getID(46);
		} else if (v.getId() == R.id.tv47) {
			bread = entry.getName(47);
			apples = entry.getHotness(47);
			almonds = entry.getID(47);
		} else if (v.getId() == R.id.tv48) {
			bread = entry.getName(48);
			apples = entry.getHotness(48);
			almonds = entry.getID(48);
		} else if (v.getId() == R.id.tv49) {
			bread = entry.getName(49);
			apples = entry.getHotness(49);
			almonds = entry.getID(49);
		} else if (v.getId() == R.id.tv50) {
			bread = entry.getName(50);
			apples = entry.getHotness(50);
			almonds = entry.getID(50);
		}
		/*basket.putString("key", bread);
		basket.putString("desc", apples);
		*/basket.putString("id", almonds);
		for (int i = 0; i < 50; i++) {
			tv[i].setEnabled(false);
		}
		// Intent openStartingPoint = new Intent("com.example.timet.DUMMY");
		
		try {
			Intent openStartingPoint = new Intent();
			openStartingPoint.setClass(TimeTableActivity.this, Dummy.class);
			openStartingPoint.putExtras(basket);
			startActivity(openStartingPoint);

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, "Try Again",Toast.LENGTH_SHORT).show();
		}
		// startActivity(openStartingPoint);
		entry.close();

	}

	/*
	 * @Override protected void onActivityResult(int requestCode, int
	 * resultCode, Intent data) { if (resultCode == RESULT_OK && requestCode ==
	 * REQUEST_CODE) { if (data.hasExtra("returnKey1")) { int b =
	 * data.getExtras().getInt("returnKey2"); String gotcheery =
	 * data.getExtras().getString("returnKey1"); HotOrNot entry = new
	 * HotOrNot(this); try { entry.open(); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } entry.updateEntry(b,
	 * gotcheery, entry.getHotness(b)); entry.close();
	 * 
	 * Toast.makeText(this,
	 * "change will be seen when you again open time table",
	 * Toast.LENGTH_LONG).show();
	 * 
	 * } } }
	 */

	public class loadSomeStuff extends AsyncTask<String, Void, String> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(TimeTableActivity.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			HotOrNot entry1;
			entry1 = new HotOrNot(TimeTableActivity.this);
			try {
				entry1.open();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (int i = 0; i < 50; i++) {
				int i1 = i + 1;
				name1 = entry1.getName(i1);
				if (name1 != null) {
					System.out.println("name: " + name1);
					tv[i].setText(name1);
				}

			}
			entry1.close();

			/*
			 * try { Thread.sleep(5000); } catch (Exception e) { // TODO: handle
			 * exception }
			 */

			return "";
		}

		@Override
		protected void onPostExecute(String str) {
			dialog.dismiss();

		}
	}
}
