package utilities;

import javax.swing.SwingUtilities;

import factory.Gui.Mainframe;

public class program {

		public static void main(String[] args) {
			
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new Mainframe();
					
	
				}
			});
		

		}
}
