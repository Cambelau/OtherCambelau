   M S B A M L   `   `   `  ����E  AAssigment2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null'clr-namespace:Assigment2
Assigment2  5   	   !    Assigment2.DoctorManagement   5   	   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   *localclr-namespace:Assigment2     5   	     � WindowStartupLocation$  CenterScreen=�5   	    � Title$ DoctorManagement��5	   	   $	��450��6"   $	��800��6/   .��5
      � 
�������.+�5   
   �� $��Doctor Management��6   $
��Left=�6,   $��	68,32,0,0q�6G   $	��Top=�6Z   $	��294��6r   $��24(�6~   � 5   
   $��68,97,278,29q�6   .+�5      �� 
���   �$��1q�6)   $��0,0,0,10q�6=   �� 5      $
��Left=�6   $	��Top=�64   $��
227,23,0,0q�6L   $	��221��6`   $	��190��6m   .+�5      �� -   5         ��Name$ CodeTextBox��5      $��0 0 0 20q�6/   $
��Left=�6A    �� TextWrapping$
 Wrap=�6]   $	��Top=�6r   $	��190��6�   �� 5      -   5        $ NameTextBox��5      $��0 0 0 20q�6/   $
��Left=�6A   $
 Wrap=�6\   $	��Top=�6q   $	��190��6�   �� 5      -   5        $ TelephoneTextBox��5      $��0 0 0 20q�64   $
��Left=�6F   $
 Wrap=�6a   $	��Top=�6u   $	��190��6�   (  "System.Windows.Controls.DatePicker  5      -   5          Name$ HiringDateDateTime��5      $��0 0 0 20q�69   $
��Left=�6K   $	��Top=�6g   $	��190��6   �� 5      -   5        $��SpecialityComboBox��5   ?    �� 
IsEditable . $��0 0 0 20q�6-   $	��190��6Z   $��23��6f   .@�5      �� .��6#   	Général6,   �� 5      .��6$   Cardio6,   �� 5      .��6$   	Chirurgie6/   �� 5      .��6$   	Dermato6-   �� 5      .��6$   
Pédiatrie6/   5      5      �� 5      $
��Code��6   $
��Left=�6#   $��	57,34,0,0q�6>   $	��Top=�6Q   �� 5      $
��Name��6   $
��Left=�6#   $��	57,73,0,0q�6>   $	��Top=�6Q   �� 5      $��	Telephone��6   $
��Left=�6(   $��
57,111,0,0q�6C   $	��Top=�6W   �� 5      $��Hiring Date��6   $
��Left=�6*   $��
57,143,0,0q�6E   $	��Top=�6Y   �� 5      $��
Speciality��6   $
��Left=�6)   $��
57,181,0,0q�6D   $	��Top=�6X   5       �� 5!   
   -   5        $��StackPanel3��5!   0   $
��Left=�6   $	��Top=�6C   $��
575,97,0,0q�6[   $	��221��6o   $	��173��6|   .+�5"      �� -   5        $��0 0 0 20q�5"      $	��New��6'   65   �� 5#      -   5        $��0 0 0 20q�5#      $��Search��6'   68   �� 5$      -	   5        $��0 0 0 20q�5$      $	��Add��6'   65   �� 5%      -
   5        $��0 0 0 20q�5%      $
��Edit��6'   66   �� 5&      -   5        $��0 0 0 20q�5&      $��Delete��6'   68   �� 5'      -   5        $��0 0 0 20q�5'      $
��Exit��6'   66   5(      �� 5)   
   -   5         ��Name$ 	errorText��5)   %   $y�Hidden=�6   $��Error Happened ! ��68   $
��Left=�6T   $��575,342,0,0q�6o   $	��Top=�6�   $	��173��6�   ���	#FFEB2020$��9(�6�   5*      5+      