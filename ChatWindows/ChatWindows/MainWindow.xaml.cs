using ChatWindows.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ChatWindows
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        { 
            this.DataContext = new DataCtx();
            InitializeComponent();             

        }

        private void idUsario_Click(object sender, RoutedEventArgs e)
        {            
            Contacts ventanaContactos = new Contacts((DataCtx)this.DataContext);
            ventanaContactos.Show();
            this.Close();
        }
    }
}
