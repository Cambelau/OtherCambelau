   M S B A M L   `   `   `  ����E  AAssigment2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null'clr-namespace:Assigment2
Assigment2  5   	   "    Assigment2.ShowPatientWindow   5   	   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   *localclr-namespace:Assigment2     5   	     � WindowStartupLocation$  CenterScreen=�5   	    � Title$ ShowPatientWindow��5	   	   $	��563��6#   $	��800��60   .��5
      � $��0,0,0,-6q�6   .+�5   
   �� $��Enter Patient Code��6   $
��Left=�6-   $��	42,13,0,0q�6H   $	��Top=�6[   $��18(�6s   �� 5   
   -   5        $��Search��5   &   $
��Left=�67   $��
646,37,0,0q�6R   $	��Top=�6f   $��91��6~   �� 5   
   -   5        $��Cancel��5   &   $
��Left=�67   $��
646,71,0,0q�6R   $	��Top=�6f   $��91��6~   �� 5   
   $
��Code��6   $
��Left=�6   $��	83,52,0,0q�6:   $	��Top=�6M   �� 5   
   -   5         ��Name$ PatientCodeTextBox��5      $
��Left=�6.   $��
158,56,0,0q�6I    �� TextWrapping$
 Wrap=�6]   $	��Top=�6r   $	��120��6�   �� 5   
   $��Patient Info��6   $
��Left=�6'   $��
54,120,0,0q�6B   $	��Top=�6V   $��18(�6n   �� 5   
   $
��Name��6   $
��Left=�6   $��
83,168,0,0q�6:   $	��Top=�6N   �� 5   
   -   5        $ PatientNameTextBox��5      $
��Left=�6.   $��158,172,0,0q�6I   $
 Wrap=�6^   $	��Top=�6s   $	��120��6�   �� 5   
   $��Address��6   $
��Left=�6"   $��351,168,0,0q�6=   $	��Top=�6R   �� 5   
   -   5        $ AddressTextBox��5      $
��Left=�6*   $��426,172,0,0q�6E   $
 Wrap=�6Z   $	��Top=�6o   $	��120��6�   �� 5   
   $��
Birth Date��6   $
��Left=�6%   $��83,0,0,0q�6@   $��Center=�6R   �� 5   
   -   5        $ BirthDateTextBox��5      $
��Left=�6,   $��	158,0,0,0q�6G   $
 Wrap=�6Z   $��Center=�6o   $	��120��6�   &   System.Windows.Controls.DataGrid  5   
   -   5          Name$ dgAppointment��5   I   $��23,223,23,175q�6a   �� 5   
   -   5         ��Name$ 	errorText��5   %   $y�Hidden=�6   $��Error Happened ! ��68   $
��Left=�6T   $��564,105,0,0q�6o   $	��Top=�6�   $	��173��6�   ���	#FFEB2020$��9(�6�   �� 5   
   $��Appointment��6   $
��Left=�6&   $��
42,398,0,0q�6A   $	��Top=�6U   $	��175��6m   $��20(�6y   �� 5   
   $��Appointment Code��6   $
��Left=�6+   $��
66,440,0,0q�6F   $	��Top=�6Z   �� 5   
   $��Appointment Date��6   $
��Left=�6+   $��
67,480,0,0q�6F   $	��Top=�6Z   �� 5   
   -	   5        $ codeTextBox��5      $
��Left=�6'   $��218,444,0,0q�6B   $
 Wrap=�6W   $	��Top=�6k   $	��120��6�   (  "System.Windows.Controls.DatePicker  5   
   -
   5          Name$ dateTextBox��5      $
��Left=�6*   $��218,480,0,0q�6E   $	��Top=�6Z   $	��120��6r   �� 5   
   $��Appointment Time��6   $
��Left=�6+   $��377,440,0,0q�6F   $	��Top=�6[   $	��110��6s   �� 5   
   $��
DoctorCode��6   $
��Left=�6%   $��377,483,0,0q�6@   $	��Top=�6U   $	��110��6m   �� 5    
   -   5        $ timeTextBox��5       $
��Left=�6'   $��510,444,0,0q�6B   $
 Wrap=�6W   $	��Top=�6k   $	��140��6�   �� 5!   
   $
��Save��6   $
��Left=�6    $��702,451,0,0q�6;   $	��Top=�6P   $��56��6h   �� 5"   
   $��Delete��6   $
��Left=�6"   $��702,488,0,0q�6=   $	��Top=�6R   $��56��6j   �� 5#   
   -   5        $ 
docTextBox��5#      $
��Left=�6&   $��510,487,0,0q�6A   $
 Wrap=�6V   $	��Top=�6k   $	��140��6�   5$      5%      