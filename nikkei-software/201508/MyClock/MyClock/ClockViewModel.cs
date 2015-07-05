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

        public ClockViewModel()
        {
            clock.Tick += (sender, currentTime) => DigitalDisplay = currentTime.ToLongTimeString();
        }

        private void RaisePropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
