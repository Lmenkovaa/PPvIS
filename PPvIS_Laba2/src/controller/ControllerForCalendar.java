package controller;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ControllerForCalendar {

    public JDatePickerImpl createCalendar(JDatePickerImpl datePicker, int x, int y, JPanel panel) {
        Properties properties = new Properties();
        properties.put("text.today", "today");
        properties.put("text.month", "month");
        properties.put("text.year", "year");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        datePicker.setMaximumSize(new Dimension(x,y));
        panel.add(datePicker);

        return datePicker;
    }


}
