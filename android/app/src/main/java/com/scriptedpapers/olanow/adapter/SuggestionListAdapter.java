package com.scriptedpapers.olanow.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.scriptedpapers.olanow.data.SuggestionItem;
import com.scriptedpapers.olanow.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mahes on 26/9/15.
 */
public class SuggestionListAdapter extends ArrayAdapter<SuggestionItem> {

    ArrayList<SuggestionItem> suggestionList;
    Context context;


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

        holder.rideNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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

        @InjectView(R.id.rideNowButton) TextView rideNowButton;
        @InjectView(R.id.eventName) TextView eventName;
        @InjectView(R.id.eventTime) TextView eventTime;
        @InjectView(R.id.eventLocationLayout)
        LinearLayout eventLocationLayout;
        @InjectView(R.id.eventLocation) TextView eventLocation;


        @InjectView(R.id.messagesIcon)
        ImageView messagesIcon;
        @InjectView(R.id.eventsIcon) ImageView eventsIcon;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);

            todayView.setVisibility(View.GONE);
            eventLayout.setVisibility(View.GONE);
            mesageLayout.setVisibility(View.GONE);

            detailView.setVisibility(View.GONE);

            messagesIcon.setVisibility(View.GONE);
            eventsIcon.setVisibility(View.GONE);
        }
    }
}
