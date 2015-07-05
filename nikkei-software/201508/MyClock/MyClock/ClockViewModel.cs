using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyClock
{
    public class ClockViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        Clock clock = new Clock();

        string digitalDisplay = string.Empty;

        public string DigitalDisplay
        {
            get
            {
                return digitalDisplay;
            }
            private set
            {
                if (digitalDisplay != value)
                {
                    digitalDisplay = value;
                    RaisePropertyChanged("DigitalDisplay");
                }
            }
        }

        double hourHandAngle = 0.0;

        public double HourHandAngle
        {
            get
            {
                return hourHandAngle;
            }
            private set
            {
                if (hourHandAngle != value)
                {
                    hourHandAngle = value;
                    RaisePropertyChanged("HourHandAngle");
                }
            }
        }

        double minuteHandAngle = 0.0;

        public double MinuteHandAngle
        {
            get
            {
                return minuteHandAngle;
            }
            private set
            {
                if (minuteHandAngle != value)
                {
                    minuteHandAngle = value;
                    RaisePropertyChanged("MinuteHandAngle");
                }
            }
        }

        double secondHandAngle = 0.0;

        public double SecondHandAngle
        {
            get
            {
                return secondHandAngle;
            }
            private set
            {
                if (secondHandAngle != value)
                {
                    secondHandAngle = value;
                    RaisePropertyChanged("SecondHandAngle");
                }
            }
        }

        void UpdateHandAngle(DateTime time)
        {
            SecondHandAngle = time.Second * 360.0 / 60.0;
            MinuteHandAngle = (time.Minute + time.Second / 60.0) * 360.0 / 60.0;
            HourHandAngle = (time.Hour + time.Minute / 60.0) * 360.0 / 12.0;
        }

        public ClockViewModel()
        {
            clock.Tick += (sender, currentTime) =>
            {
                DigitalDisplay = currentTime.ToLongTimeString();
                UpdateHandAngle(currentTime);
            };
        }

        private void RaisePropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
