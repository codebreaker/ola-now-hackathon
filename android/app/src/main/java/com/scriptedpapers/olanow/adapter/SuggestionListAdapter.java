package com.scriptedpapers.olanow.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.scriptedpapers.olanow.OlaNow;
import com.scriptedpapers.olanow.data.BookedRide;
import com.scriptedpapers.olanow.data.FareBreakUp;
import com.scriptedpapers.olanow.data.RideCategory;
import com.scriptedpapers.olanow.data.SuggestionItem;
import com.scriptedpapers.olanow.R;
import com.scriptedpapers.olanow.data.response.EstimateRideResponse;
import com.scriptedpapers.olanow.data.response.RideAvailabilityResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mahes on 26/9/15.
 */
public class SuggestionListAdapter extends ArrayAdapter<SuggestionItem> {

    public static final double PICKUP_LAT = 12.949920;
    public static final double PICKUP_LNG = 77.643933;
    ArrayList<SuggestionItem> suggestionList;
    Context context;
    private View.OnClickListener l;


    public SuggestionListAdapter(Context context, ArrayList<SuggestionItem> suggestionList) {
        super(context, 0, suggestionList);

        this.context = context;
        this.suggestionList = suggestionList;
    }

    @ Override
    public View getView ( int position, View convertView, ViewGroup parent )
    {


        LayoutInflater mInflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        convertView =    mInflater.inflate(R.layout.lyt_suggestion_item, null)  ;

       SuggestionItem suggestion = suggestionList.get(position);

        ViewHolder holder = new ViewHolder(convertView);

        l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.show();

                final String token = context.getResources().getString(R.string.x_app_token);
                final String authToken = context.getResources().getString(R.string.test_auth_token);
                OlaNow.getOlaService().getRideAvailability(token, PICKUP_LAT, PICKUP_LNG, null, new Callback<RideAvailabilityResponse>() {
                    @Override
                    public void success(RideAvailabilityResponse checkRideResponse, Response response) {
                        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.MyAlertDialogStyle);
                        dialog.setContentView(R.layout.dialog_book_a_ride);
                        dialog.setTitle("Book a Ride");

                        final View mainView = dialog.findViewById(R.id.lyt_main);
                        final View progressView = dialog.findViewById(R.id.lyt_progress);

                        final View sedanView = dialog.findViewById(R.id.lyt_sedan);
                        final View miniView = dialog.findViewById(R.id.lyt_mini);

                        final RadioButton rdbSedan = (RadioButton) dialog.findViewById(R.id.rdbSelectSedan);
                        final RadioButton rdbMini = (RadioButton) dialog.findViewById(R.id.rdbSelectMini);

                        final Button btnRideNow = (Button)dialog.findViewById(R.id.btnRideNow);
                        btnRideNow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                btnRideNow.setEnabled(false);
                                progressView.setVisibility(View.VISIBLE);
                                progressView.bringToFront();
                                String carCategory = rdbMini.isChecked()?"mini":"sedan";
                                OlaNow.getOlaService().bookRide(token, authToken, PICKUP_LAT, PICKUP_LNG, "NOW", carCategory, new Callback<BookedRide>() {
                                    @Override
                                    public void success(BookedRide bookedRide, Response response) {
                                        progressView.setVisibility(View.GONE);
                                        Toast.makeText(context,"Cab has been booked successfully",Toast.LENGTH_LONG).show();
                                        dialog.dismiss();


                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        progressView.setVisibility(View.GONE);
                                        Toast.makeText(context,"Cab booking failed. Please try again",Toast.LENGTH_LONG).show();
                                        btnRideNow.setEnabled(true);
                                    }
                                });
                            }
                        });

                        sedanView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                rdbMini.setChecked(false);
                                rdbSedan.setChecked(true);
                                btnRideNow.setEnabled(true);
                            }
                        });

                        miniView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                rdbMini.setChecked(true);
                                rdbSedan.setChecked(false);
                                btnRideNow.setEnabled(true);
                            }
                        });

                        TextView txtMinDistanceMini = (TextView) dialog.findViewById(R.id.txtMinDistanceMini);
                        TextView txtMindistanceSedan = (TextView) dialog.findViewById(R.id.txtMinDistanceSedan);

                        TextView txtRideTypeSedan = (TextView) dialog.findViewById(R.id.txtRideTypeSedan);
                        TextView txtRideTypeMini = (TextView) dialog.findViewById(R.id.txtRideTypeMini);

                        TextView baseFareSedan = (TextView) dialog.findViewById(R.id.baseFareSedan);
                        TextView baseFareMini = (TextView) dialog.findViewById(R.id.txtBaseFareMini);

                        TextView txtCostPerDistanceSedan = (TextView) dialog.findViewById(R.id.txtCostPerDistanceSedan);
                        TextView txtCostPerDistanceMini = (TextView) dialog.findViewById(R.id.txtCostPerDistanceMini);


                        int categorySize = checkRideResponse.getRideCategoryList().size();
                        if (categorySize > 0) {
                            if (categorySize == 2) {
                                for (RideCategory category : checkRideResponse.getRideCategoryList()) {
                                    if (category.getFareBreakUpList() == null) {
                                        if ("sedan".equalsIgnoreCase(category.getRideId())) {
                                            sedanView.setVisibility(View.GONE);
                                        } else {
                                            miniView.setVisibility(View.GONE);
                                        }
                                        continue;
                                    }
                                    FareBreakUp fareBreakUp = category.getFareBreakUpList().get(0);
                                    if ("sedan".equalsIgnoreCase(category.getRideId())) {
                                        txtMindistanceSedan.setText(category.getDistance() + " " + category.getDistanceUnit());
                                        txtRideTypeSedan.setText(category.getDisplayName());
                                        baseFareSedan.setText("Starts from " + category.getCurrencyType() + ". " + fareBreakUp.getBaseFare());
                                        txtCostPerDistanceSedan.setText(category.getCurrencyType() + "." + fareBreakUp.getCostPerDistance() + " ( per " + category.getDistanceUnit() + ")");
                                    } else {
                                        txtMinDistanceMini.setText(category.getDistance() + " " + category.getDistanceUnit());
                                        txtRideTypeMini.setText(category.getDisplayName());
                                        baseFareMini.setText(category.getCurrencyType() + ". " + fareBreakUp.getBaseFare());
                                        txtCostPerDistanceMini.setText(category.getCurrencyType() + ". " + fareBreakUp.getBaseFare() + " ( per " + category.getDistanceUnit() + ")");
                                    }
                                }

                            } else {
                                RideCategory category = checkRideResponse.getRideCategoryList().get(0);
                                if ("sedan".equalsIgnoreCase(category.getRideId())) {
                                    miniView.setVisibility(View.GONE);
                                    txtMindistanceSedan.setText(category.getDistance() + " " + category.getDistanceUnit());
                                    txtRideTypeSedan.setText(category.getDisplayName());
                                    baseFareSedan.setText(category.getCurrencyType() + ". " + category.getDistance());
                                    txtCostPerDistanceSedan.setText(category.getDistance() + " " + category.getDistanceUnit());
                                } else {
                                    sedanView.setVisibility(View.GONE);
                                    txtMinDistanceMini.setText(category.getDistance() + " " + category.getDistanceUnit());
                                    txtRideTypeMini.setText(category.getDisplayName());
                                    baseFareMini.setText(category.getCurrencyType() + ". " + category.getDistance());
                                    txtCostPerDistanceMini.setText(category.getDistance() + " " + category.getDistanceUnit());
                                }
                            }
                        }

                        progressDialog.dismiss();
                        dialog.show();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                    }
                });


//                // set the custom dialog components - text, image and button
//                TextView text = (TextView) dialog.findViewById(R.id.text);
//                text.setText("Android custom dialog example!");
//                ImageView image = (ImageView) dialog.findViewById(R.id.image);
//                image.setImageResource(R.drawable.ic_launcher);

//                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
//                // if button is clicked, close the custom dialog
//                dialogButton.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });

            }
        };
        holder.rideNowButton.setOnClickListener(l);

        if(suggestion.isToday()) {

            SimpleDateFormat df = new SimpleDateFormat("yyyy MMMM, dd");
            String formattedDate = df.format(new Date().getTime());


            holder.todayView.setVisibility(View.VISIBLE);
            holder.todayView.setText("Today ("+formattedDate+")");
        } else if(suggestion.getEvent() != null) {

            holder.detailView.setVisibility(View.VISIBLE);

            holder.eventLayout.setVisibility(View.VISIBLE);

            holder.eventsIcon.setVisibility(View.VISIBLE);

            SimpleDateFormat df = new SimpleDateFormat("hh:mm a ");
            String formattedDate = df.format(suggestion.getEvent().getEventDate().getTime());

            holder.eventName.setText(suggestion.getEvent().getEventName());
            holder.eventTime.setText(formattedDate);

            if(suggestion.getEvent().getEventLocation() == null || suggestion.getEvent().getEventLocation().length() == 0) {
                holder.eventLocationLayout.setVisibility(View.INVISIBLE);
            } else {
                holder.eventLocationLayout.setVisibility(View.VISIBLE);
                holder.eventLocation.setText(suggestion.getEvent().getEventLocation());
            }

        } else if(suggestion.isMessageTrainTicket()) {

            holder.messagesIcon.setVisibility(View.VISIBLE);

            holder.detailView.setVisibility(View.VISIBLE);
            holder.mesageLayout.setVisibility(View.VISIBLE);
        } else if(suggestion.getReminder() != null) {

            holder.reminderLayout.setVisibility(View.VISIBLE);

            holder.detailView.setVisibility(View.VISIBLE);
            holder.ReminderName.setText(suggestion.getReminder().getReminderName());


            SimpleDateFormat df = new SimpleDateFormat("hh:mm a ");
            String formattedDate = df.format(suggestion.getReminder().getReminderDate());

            holder.reminderTime.setText(formattedDate);
        }

        return convertView ;
    }

    static class ViewHolder {
        @InjectView(R.id.todayView) TextView todayView;

        @InjectView(R.id.detailView)
        CardView detailView;

        @InjectView(R.id.eventLayout)
        LinearLayout eventLayout;

        @InjectView(R.id.mesageLayout)
        LinearLayout mesageLayout;

        @InjectView(R.id.reminderLayout)
        LinearLayout reminderLayout;

        @InjectView(R.id.rideNowButton) TextView rideNowButton;
        @InjectView(R.id.eventName) TextView eventName;
        @InjectView(R.id.eventTime) TextView eventTime;
        @InjectView(R.id.eventLocationLayout)
        LinearLayout eventLocationLayout;
        @InjectView(R.id.eventLocation) TextView eventLocation;


        @InjectView(R.id.messagesIcon)
        ImageView messagesIcon;
        @InjectView(R.id.eventsIcon) ImageView eventsIcon;


        @InjectView(R.id.ReminderName) TextView ReminderName;
        @InjectView(R.id.reminderTime) TextView reminderTime;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);

            todayView.setVisibility(View.GONE);
            eventLayout.setVisibility(View.GONE);
            mesageLayout.setVisibility(View.GONE);
            reminderLayout.setVisibility(View.GONE);

            detailView.setVisibility(View.GONE);

            messagesIcon.setVisibility(View.GONE);
            eventsIcon.setVisibility(View.GONE);
        }
    }
}
