/*
 * Copyright (c) 2013 Razer Inc. All rights reserved.
 */
package com.fundrun.com;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.fundrun.com.R;
import com.razer.android.nabuopensdk.AuthCheckCallback;
import com.razer.android.nabuopensdk.NabuOpenSDK;
import com.razer.android.nabuopensdk.interfaces.FitnessHistoryListener;
import com.razer.android.nabuopensdk.interfaces.FitnessListener;
import com.razer.android.nabuopensdk.interfaces.Hi5Listener;
import com.razer.android.nabuopensdk.interfaces.NabuAuthListener;
import com.razer.android.nabuopensdk.interfaces.PulseListener;
import com.razer.android.nabuopensdk.interfaces.SendNotificationListener;
import com.razer.android.nabuopensdk.interfaces.SleepHistoryListener;
import com.razer.android.nabuopensdk.interfaces.SleepTrackerListener;
import com.razer.android.nabuopensdk.interfaces.UserProfileListener;
import com.razer.android.nabuopensdk.models.Hi5Data;
import com.razer.android.nabuopensdk.models.NabuFitness;
import com.razer.android.nabuopensdk.models.NabuFitnessHistory;
import com.razer.android.nabuopensdk.models.NabuNotification;
import com.razer.android.nabuopensdk.models.NabuSleepHistory;
import com.razer.android.nabuopensdk.models.NabuSleepTracker;
import com.razer.android.nabuopensdk.models.PulseData;
import com.razer.android.nabuopensdk.models.Scope;
import com.razer.android.nabuopensdk.models.UserProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	/** The nabu sdk. */
	static NabuOpenSDK nabuSDK = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}

		nabuSDK = NabuOpenSDK.getInstance(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		Log.e("Destroyed", "Destroyed");
		nabuSDK.onDestroy(this);
		super.onDestroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		/** The btn login. */
		private Button btnLogin;

		/** The btn send. */
		private Button btnSend;

		/** The btn check app. */
		private Button btnCheckApp;

		/** The btn fitness. */
		private Button btnFitness;

		/** The btn fitness history. */
		private Button btnFitnessHistory;

		/** The btn sleep. */
		private Button btnSleep;

		/** The btn user profile. */
		private Button btnUserProfile;

		private Button btnPulse;

		private Button btnHi5;

		/** The tv result. */
		private TextView tvResult;

		/** The btn sleep history. */
		private Button btnSleepHistory;

		/** The btn set date time. */
		private Button btnSetDateTime;

		/** The tv date time. */
		private TextView tvDateTime;
		
		/** The btn clipboard. */
		private Button btnClipboard;

		/** The builder. */
		StringBuilder builder;

		/** The calendar. */
		Calendar calendar = Calendar.getInstance();

		/** The date dialog. */
		DatePickerDialog dateDialog;

		/** The reference gmt time. */
		long referenceGMTTime = 0;

		/**
		 * Instantiates a new placeholder fragment.
		 */
		public PlaceholderFragment() {
			builder = new StringBuilder();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater,
		 * android.view.ViewGroup, android.os.Bundle)
		 */
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			btnLogin = (Button) rootView.findViewById(R.id.login);
//			btnSend = (Button) rootView.findViewById(R.id.buttonNotification);
//			btnCheckApp = (Button) rootView.findViewById(R.id.buttonCheckApp);
//			btnSleepHistory = (Button) rootView.findViewById(R.id.button4_1);
//			btnFitness = (Button) rootView.findViewById(R.id.button3);
//			btnFitnessHistory = (Button) rootView.findViewById(R.id.button6);
//			btnSleep = (Button) rootView.findViewById(R.id.button4);
//			btnUserProfile = (Button) rootView.findViewById(R.id.button5);
//			btnPulse = (Button) rootView.findViewById(R.id.button5_1);
//			btnHi5 = (Button) rootView.findViewById(R.id.button5_2);
//			btnSetDateTime = (Button) rootView.findViewById(R.id.btnSetDateTime);
//			btnClipboard = (Button) rootView.findViewById(R.id.btnClipboard);
//			tvDateTime = (TextView) rootView.findViewById(R.id.tvDateTime);
//			tvResult = (TextView) rootView.findViewById(R.id.textView2);
			return rootView;
		}

		/**
		 * Sets the result.
		 * 
		 * @param s
		 *            the new result
		 */
//		public void setResult(String s) {
//			tvResult.setText(s);
//		}

		/**
		 * Show date time.
		 */
		public void showDateTime() {
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm Z");
			String localTime = sdf.format(calendar.getTime());

			referenceGMTTime = calendar.getTimeInMillis() / 1000;
			tvDateTime.setText(localTime + "\nGMT: " + referenceGMTTime);
		}

		/**
		 * Convert local time to gmt.
		 * 
		 * @param local
		 *            time in second
		 * @return GMT time in second
		 */
		private int convertLocalTimeToGMT(int local) {
			Calendar c = Calendar.getInstance();
			int offset = (c.get(Calendar.ZONE_OFFSET) + c.get(Calendar.DST_OFFSET)) / 1000;
			return local - offset;
		}

		/**
		 * Convert gm tto local.
		 * 
		 * @param gmt
		 *            time in second
		 * @return localtime in second
		 */
		private int convertGMTtoLocal(int gmt) {
			Calendar c = Calendar.getInstance();
			int offset = (c.get(Calendar.ZONE_OFFSET) + c.get(Calendar.DST_OFFSET)) / 1000;
			return gmt + offset;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
		 */
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 1);
//			dateDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
//
//				@Override
//				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//					calendar.set(Calendar.YEAR, year);
//					calendar.set(Calendar.MONTH, monthOfYear);
//					calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//					showDateTime();
//
//				}
//			}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//
//			showDateTime();
//			btnSetDateTime.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					dateDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//					dateDialog.show();
//				}
//			});

			btnLogin.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					nabuSDK.initiate(getActivity(), "153ce880ff495f953c0d3aa51b2df6f9f4b7da12", new String[] { Scope.SCOPE_FITNESS }, new NabuAuthListener() {

						@Override
						public void onAuthSuccess(String arg0) {
							Intent intent = new Intent(getActivity(), Dashboard.class);
							startActivity(intent);
						}

						@Override
						public void onAuthFailed(String arg0) {
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
					 				 
							// set dialog message
							alertDialogBuilder
								.setMessage("Authentication failed.")
								.setCancelable(false)
								.setNegativeButton("OK",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
									}
								});
				 
								// create alert dialog
								AlertDialog alertDialog = alertDialogBuilder.create();
				 
								// show it
								alertDialog.show();
							}
					});

				}
			});

//			btnSleepHistory.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					querySleepHistoryData();
//				}
//			});
//
//			btnFitness.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					queryFitnessData();
//
//				}
//			});
//
//			btnFitnessHistory.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					queryFitnessHistoryData();
//
//				}
//			});
//
//			btnSleep.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					querySleepData();
//
//				}
//			});
//
//			btnUserProfile.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					queryUserProfileData();
//
//				}
//			});
//
//			btnPulse.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					queryPulseData();
//
//				}
//			});
//
//			btnHi5.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					queryHi5Data();
//				}
//			});
//
//			btnSend.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					send();
//				}
//			});
//
//			btnCheckApp.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					checkApp();
//				}
//			});
//			
//			btnClipboard.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					Intent intent = new Intent(getActivity(), ClipboardActivity.class);
//					startActivity(intent);
//					
//				}
//			});
		}

		/**
		 * Query fitness data.
		 */
//		private void queryFitnessData() {
//			nabuSDK.getFitnessData(getActivity(), (int) (referenceGMTTime), new FitnessListener() {
//
//				@Override
//				public void onReceiveFailed(String arg0) {
//
//					builder = new StringBuilder();
//
//					builder.append(arg0);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//
//				@Override
//				public void onReceiveData(NabuFitness[] arg0) {
//
//					builder = new StringBuilder();
//
//					for (NabuFitness nabuFitness : arg0) {
//
//						// builder.append(convertGMTtoLocal((int)nabuFitness.recordTime));
//						builder.append(nabuFitness.recordTime);
//						builder.append("\n");
//						builder.append("Steps\t");
//						builder.append(nabuFitness.fitness.steps);
//						builder.append("\n");
//						builder.append("Calories\t");
//						builder.append(nabuFitness.fitness.calories);
//						builder.append("\n");
//						builder.append("Floors\t");
//						builder.append(nabuFitness.fitness.floorClaimbed);
//						builder.append("\n");
//						builder.append("Distance\t");
//						builder.append(nabuFitness.fitness.distanceWalked);
//						builder.append("\n");
//						builder.append("\n");
//						builder.append("\n");
//					}
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//				}
//			});
//		}
//
//		/**
//		 * Query sleep history data.
//		 */
//		private void querySleepHistoryData() {
//
//			nabuSDK.getSleepHistoryData(getActivity(), 10, new SleepHistoryListener() {
//
//				@Override
//				public void onReceiveFailed(String errorMessage) {
//
//					builder = new StringBuilder();
//
//					builder.append(errorMessage);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//
//				@Override
//				public void onReceiveData(NabuSleepHistory[] sleepHistory) {
//					builder = new StringBuilder();
//
//					for (NabuSleepHistory sleep : sleepHistory) {
//						builder.append(sleep.recordDate);
//						builder.append("\n");
//						builder.append("efficiency\t");
//						builder.append(sleep.efficiency);
//						builder.append("\n");
//						builder.append("good\t");
//						builder.append(sleep.good);
//						builder.append("\n");
//						builder.append("bad\t");
//						builder.append(sleep.bad);
//						builder.append("\n");
//					}
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//			});
//		}
//
//		/**
//		 * Query fitness history data.
//		 */
//		private void queryFitnessHistoryData() {
//			nabuSDK.getFitnessHistoryData(getActivity(), 30, new FitnessHistoryListener() {
//
//				@Override
//				public void onReceiveFailed(String arg0) {
//
//					builder = new StringBuilder();
//
//					builder.append(arg0);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//
//				@Override
//				public void onReceiveData(NabuFitnessHistory[] arg0) {
//
//					builder = new StringBuilder();
//
//					int record = 0;
//					for (NabuFitnessHistory nabuFitness : arg0) {
//						builder.append(record++);
//						builder.append(" ");
//						builder.append(nabuFitness.recordDate);
//						builder.append("\n");
//						builder.append("Steps\t");
//						builder.append(nabuFitness.fitness.steps);
//						builder.append("\n");
//						builder.append("Calories\t");
//						builder.append(nabuFitness.fitness.calories);
//						builder.append("\n");
//						builder.append("Floors\t");
//						builder.append(nabuFitness.fitness.floorClaimbed);
//						builder.append("\n");
//						builder.append("Distance\t");
//						builder.append(nabuFitness.fitness.distanceWalked);
//						builder.append("\n");
//						builder.append("\n");
//						builder.append("\n");
//					}
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//				}
//			});
//		}
//
//		/**
//		 * Query sleep data.
//		 */
//		private void querySleepData() {
//			nabuSDK.getSleepTrackerData(getActivity(), 1411898655000L, System.currentTimeMillis(), new SleepTrackerListener() {
//
//				@Override
//				public void onReceiveFailed(String arg0) {
//					builder = new StringBuilder();
//
//					builder.append(arg0);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//
//				@Override
//				public void onReceiveData(NabuSleepTracker[] arg0) {
//					builder = new StringBuilder();
//
//					for (NabuSleepTracker sleepTracker : arg0) {
//						builder.append(sleepTracker.startTime);
//						builder.append("\n");
//						builder.append(sleepTracker.endTime);
//
//						builder.append("\n");
//
//						List<Boolean> bool = sleepTracker.data;
//
//						int x = 0;
//						for (Boolean boolean1 : bool) {
//							x++;
//							Log.e("value", x + " ");
//							builder.append(boolean1);
//							builder.append(",");
//						}
//
//						builder.append("\n");
//
//						builder.append(sleepTracker.rawDataInMinutes);
//						builder.append("\n");
//					}
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//			});
//		}
//
//		/**
//		 * Query Pulse data.
//		 */
//		private void queryPulseData() {
//			Calendar c = Calendar.getInstance();
//			c.add(Calendar.DAY_OF_MONTH, -1);
//			nabuSDK.getPulseData(getActivity(), c.getTimeInMillis(), System.currentTimeMillis(), new PulseListener() {
//
//				@Override
//				public void onReceiveFailed(String arg0) {
//					builder = new StringBuilder();
//
//					builder.append(arg0);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//
//				@Override
//				public void onReceiveData(PulseData[] arg0) {
//					builder = new StringBuilder();
//
//					for (PulseData pulse : arg0) {
//						Calendar c = Calendar.getInstance();
//						c.setTimeInMillis(pulse.timeStamp * 1000);
//						builder.append(c.get(Calendar.DATE));
//						builder.append("\n");
//						builder.append("-------------------------");
//						builder.append("\n");
//						for (String openId : pulse.openIds) {
//							builder.append(openId);
//							builder.append("\n");
//						}
//						builder.append("-------------------------");
//						builder.append("\n");
//					}
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//			});
//		}
//
//		/**
//		 * Query Hi5 data.
//		 */
//		private void queryHi5Data() {
//			Calendar c = Calendar.getInstance();
//			c.add(Calendar.DAY_OF_MONTH, -1);
//			nabuSDK.getHi5Data(getActivity(), c.getTimeInMillis(), System.currentTimeMillis(), new Hi5Listener() {
//
//				@Override
//				public void onReceiveFailed(String arg0) {
//					builder = new StringBuilder();
//
//					builder.append(arg0);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//
//				@Override
//				public void onReceiveData(Hi5Data[] arg0) {
//					builder = new StringBuilder();
//
//					for (Hi5Data data : arg0) {
//						builder.append(data.timeStamp);
//						builder.append(" -> ");
//						builder.append(data.openId);
//						builder.append("\n");
//					}
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//			});
//		}

		/**
		 * Query user profile data.
		 */
//		private void queryUserProfileData() {
//			nabuSDK.getUserProfile(getActivity(), new UserProfileListener() {
//
//				@Override
//				public void onReceiveFailed(String arg0) {
//					builder = new StringBuilder();
//
//					builder.append(arg0);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//				}
//
//				@Override
//				public void onReceiveData(UserProfile arg0) {
//					builder = new StringBuilder();
//
//					builder.append(arg0.nickName);
//					builder.append("\n");
//					builder.append(arg0.avatarUrl);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//			});
//		}

		/**
		 * Send.
		 */
//		private void send() {
//			nabuSDK.sendNotification(getActivity(), new NabuNotification("1.bin", "Top Line", "Bottom Line"), new SendNotificationListener() {
//
//				@Override
//				public void onSuccess(String arg0) {
//
//					builder = new StringBuilder();
//
//					builder.append(arg0);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//
//				@Override
//				public void onFailed(String arg0) {
//					builder = new StringBuilder();
//
//					builder.append(arg0);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//			});
//		}

		/**
		 * Check app.
		 */
//		private void checkApp() {
//			nabuSDK.checkAppAuthorized(getActivity().getApplicationContext(), new AuthCheckCallback() {
//
//				@Override
//				public void onSuccess(boolean isAuthorized) {
//					// LOGIN SUCCESSFUL
//
//					builder = new StringBuilder();
//					builder.append("isAuthorized:");
//					builder.append(Boolean.toString(isAuthorized));
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//				}
//
//				@Override
//				public void onFailed(String errorMessage) {
//					// LOGIN FAILED
//					builder = new StringBuilder();
//
//					builder.append(errorMessage);
//
//					if (builder.length() == 0)
//						builder.append("No Result");
//
//					setResult(builder.toString());
//
//				}
//			});
//		}
	}

}
