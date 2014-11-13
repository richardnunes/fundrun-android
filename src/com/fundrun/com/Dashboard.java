/*
 * 
 */
package com.fundrun.com;


import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fundrun.com.R;
import com.razer.android.nabuopensdk.NabuOpenSDK;
import com.razer.android.nabuopensdk.interfaces.CreateUpdateClipboardListener;
import com.razer.android.nabuopensdk.interfaces.DeleteClipboardListener;
import com.razer.android.nabuopensdk.interfaces.FitnessListener;
import com.razer.android.nabuopensdk.interfaces.PulseListener;
import com.razer.android.nabuopensdk.interfaces.ReadClipboardListener;
import com.razer.android.nabuopensdk.interfaces.SendNotificationListener;
import com.razer.android.nabuopensdk.models.ClipboardData;
import com.razer.android.nabuopensdk.models.NabuFitness;
import com.razer.android.nabuopensdk.models.NabuNotification;
import com.razer.android.nabuopensdk.models.PulseData;

public class Dashboard extends Activity {

	/** The nabu sdk. */
	static NabuOpenSDK nabuSDK = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new DashboardFragment()).commit();
		}

		nabuSDK = NabuOpenSDK.getInstance(this);

	}

	public static class DashboardFragment extends Fragment {
		
		Button bt3, bt4;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_dashboard,
					container, false);
			
			bt3 = (Button) rootView.findViewById(R.id.bt3);
			bt4 = (Button) rootView.findViewById(R.id.bt4);
//			edtMessage = (EditText) rootView.findViewById(R.id.tvMessage);
//			edtPrivateData = (EditText) rootView
//					.findViewById(R.id.tvPrivateData);
//			edtPublicData = (EditText) rootView.findViewById(R.id.tvPublicData);
//			btnCreateUpdate = (Button) rootView
//					.findViewById(R.id.btnCreateUpdate);
//			btnReadMyClipboard = (Button) rootView
//					.findViewById(R.id.btnReadMyClipboard);
//			btnBatchRead = (Button) rootView
//					.findViewById(R.id.btnReadClipboard);
//			btnDelete = (Button) rootView.findViewById(R.id.btnDelete);
//			tvResult = (TextView) rootView.findViewById(R.id.tvResult);

			return rootView;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			Timer tm = new Timer("FitnessPoll");
			tm.scheduleAtFixedRate( new TimerTask() {
				FitnessListener lst = new FitnessListener() {
					@Override
					public void onReceiveData(NabuFitness[] fitness) {
	
					}

					@Override
					public void onReceiveFailed(String message) {

					}
				};
				
				@Override
				public void run() {
					nabuSDK.getFitnessData(getActivity(), 01, lst);
				}
				
			}, 0, 20000);
			
			bt3.setOnClickListener( new OnClickListener() {
				@Override
				public void onClick(View v) {
					NabuNotification message = new NabuNotification("FUNdRun", "Evt Rcvd");
		
					nabuSDK.sendNotification(getActivity(), message,
							new SendNotificationListener() {
		
								@Override
								public void onFailed(String arg0) {
									AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
					 				 
									// set dialog message
									alertDialogBuilder
										.setMessage(arg0)
										.setCancelable(false)
										.setNegativeButton("OK",new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog,int id) {
												dialog.cancel();
											}
										});
									AlertDialog alertDialog = alertDialogBuilder.create();
									alertDialog.show();
								}
		
								@Override
								public void onSuccess(String arg0) {
									AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
					 				 
									// set dialog message
									alertDialogBuilder
										.setMessage(arg0)
										.setCancelable(false)
										.setNegativeButton("OK",new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog,int id) {
												dialog.cancel();
											}
										});
									AlertDialog alertDialog = alertDialogBuilder.create();
									alertDialog.show();
								}
							});
					
				}
				
			});

//			btnCreateUpdate.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					createUpdateClipboard();
//				}
//			});
//
//			btnReadMyClipboard.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					readMyClipboard();
//
//				}
//			});
//
//			btnBatchRead.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					batchReadClipboard();
//
//				}
//			});
//
//			btnDelete.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					deleteMyClipboard();
//
//				}
//			});
		}

//		protected void createUpdateClipboard() {
//			String message, privateData, publicData;
//			message = edtMessage.getText().toString();
//			privateData = edtPrivateData.getText().toString();
//			publicData = edtPublicData.getText().toString();
//
//			if (TextUtils.isEmpty(message) && TextUtils.isEmpty(privateData)
//					&& TextUtils.isEmpty(publicData)) {
//				setResult("Fields are empty.");
//				return;
//			}
//
//			nabuSDK.createUpdateClipboard(getActivity(), message,
//					privateData.getBytes(Charset.forName("UTF-8")),
//					publicData.getBytes(Charset.forName("UTF-8")),
//					new CreateUpdateClipboardListener() {
//
//						@Override
//						public void onReceiveFailed(String arg0) {
//							setResult(arg0);
//						}
//
//						@Override
//						public void onReceiveData() {
//							setResult("Create / Update Successful");
//
//						}
//					});
//		}
//
//		protected void readMyClipboard() {
//
//			nabuSDK.readMyClipboard(getActivity(), new ReadClipboardListener() {
//
//				@Override
//				public void onReceiveFailed(String e) {
//					setResult(e);
//
//				}
//
//				@Override
//				public void onReceiveData(ClipboardData[] data) {
//					if (data != null && data.length > 0) {
//						builder = new StringBuilder();
//						String priData = "", pubData = "";
//						try {
//							if (data[0].getPrivateData() != null)
//								priData = new String(data[0].getPrivateData(),
//										"UTF-8");
//						} catch (UnsupportedEncodingException e) {
//							e.printStackTrace();
//						}
//						try {
//							if (data[0].getPublicData() != null)
//								pubData = new String(data[0].getPublicData(),
//										"UTF-8");
//						} catch (UnsupportedEncodingException e) {
//							e.printStackTrace();
//						}
//						builder.append("Message: ")
//								.append(data[0].getMessage())
//								.append("\nPrivate: ").append(priData)
//								.append("\nPublic: ").append(pubData);
//						setResult(builder.toString());
//					}
//
//				}
//			});
//		}
//
//		protected void deleteMyClipboard() {
//			nabuSDK.deleteMyClipboard(getActivity(),
//					new DeleteClipboardListener() {
//
//						@Override
//						public void onReceiveFailed(String e) {
//							setResult(e);
//
//						}
//
//						@Override
//						public void onReceiveData() {
//							setResult("Delete Clipboard successful.");
//
//						}
//					});
//		}
//
//		protected void batchReadClipboard() {
//			Calendar c = Calendar.getInstance();
//			c.add(Calendar.DAY_OF_MONTH, -1);
//			nabuSDK.getPulseData(getActivity(), c.getTimeInMillis(), System.currentTimeMillis(),
//					new PulseListener() {
//
//						@Override
//						public void onReceiveFailed(String e) {
//							setResult(e);
//
//						}
//
//						@Override
//						public void onReceiveData(PulseData[] data) {
//
//							if (data != null && data.length > 0) {
//
//								String[] openIds = data[0].openIds;
//
//								nabuSDK.ReadClipboard(getActivity(), openIds,
//										new ReadClipboardListener() {
//
//											@Override
//											public void onReceiveFailed(String e) {
//												setResult(e);
//
//											}
//
//											@Override
//											public void onReceiveData(
//													ClipboardData[] data) {
//												if (data != null
//														&& data.length > 0) {
//													builder = new StringBuilder();
//													for (int i = 0; i < data.length; i++) {
//														String pubData = "";
//														try {
//															pubData = new String(
//																	data[i].getPublicData(),
//																	"UTF-8");
//														} catch (UnsupportedEncodingException e) {
//															e.printStackTrace();
//														}
//														builder.append(
//																"OpenId: ")
//																.append(data[i]
//																		.getOpenId())
//																.append("\nMessage: ")
//																.append(data[i]
//																		.getMessage())
//																.append("\nPublic: ")
//																.append(pubData)
//																.append("\n");
//
//													}
//													setResult(builder
//															.toString());
//												}
//												else{
//													setResult("No data found.");
//												}
//											}
//										});
//							} else {
//								setResult("No pulse data found.");
//							}
//
//						}
//					});
//
//		}
//
//		public void setResult(String s) {
//			tvResult.setText(s);
//		}
	}
}
