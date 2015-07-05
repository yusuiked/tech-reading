using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Threading;

namespace MyClock
{
    class Clock
    {
        public event EventHandler<DateTime> Tick;

        DispatcherTimer timer = new DispatcherTimer { Interval = TimeSpan.FromMilliseconds(100) };

        public Clock()
        {
            timer.Tick += (sender, e) =>
            {
                if (Tick != null)
                    Tick(sender: this, e: DateTime.Now);
            };
            timer.Start();
        }
    }
}
