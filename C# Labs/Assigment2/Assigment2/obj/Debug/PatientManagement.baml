   M S B A M L   `   `   `  ����E  AAssigment2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null'clr-namespace:Assigment2
Assigment2  5   	   "    Assigment2.PatientManagement   5   	   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   *localclr-namespace:Assigment2     5   	     � WindowStartupLocation$  CenterScreen=�5   	    � Title$ PatientManagement��5	   	   $	��450��6#   $	��800��60   .��5
      � .+�5   
   �� $��Patient Management��6   $
��Left=�6-   $��	50,32,0,0q�6H   $	��Top=�6[   $	��294��6s   $��24(�6   � 5   
   $��50,97,296,28q�6   .+�5      �� 
���   �$��1q�6)   $��0,0,0,10q�6=   �� 5      $
��Left=�6   $	��Top=�64   $��
227,23,0,0q�6L   $	��221��6`   $	��190��6m   .+�5      �� -   5         ��Name$ CodeTextBox��5      $��0 0 0 20q�6/   $
��Left=�6A    �� TextWrapping$
 Wrap=�6]   $	��Top=�6r   $	��190��6�   �� 5      -   5        $ NameTextBox��5      $��0 0 0 20q�6/   $
��Left=�6A   $
 Wrap=�6\   $	��Top=�6q   $	��190��6�   (  "System.Windows.Controls.DatePicker  5      -   5          Name$ BirthDateDateTime��5      $��0 0 0 20q�68   $
��Left=�6J   $	��Top=�6f   $	��190��6~   �� 5      -   5        $ AddressTextBox��5      $��0 0 0 20q�62   $
��Left=�6D   $
 Wrap=�6_   $	��Top=�6s   $	��190��6�   	� 5      -   5         	�Name$ FeminineRadioButton��5      $��Feminine��6;   	� 5      -   5        $ MasculineRadioButton��5      $��	Masculine��6<   5      �� 5      $
��Code��6   $
��Left=�6#   $��	57,18,0,0q�6>   $	��Top=�6Q   �� 5      $
��Name��6   $
��Left=�6#   $��	57,57,0,0q�6>   $	��Top=�6Q   �� 5      $��
Birth Date��6   $
��Left=�6)   $��
52,101,0,0q�6D   $	��Top=�6X   �� 5      $��Address��6   $
��Left=�6&   $��
57,143,0,0q�6A   $	��Top=�6U   �� 5      $��Gender��6   $
��Left=�6%   $��
57,181,0,0q�6@   $	��Top=�6T   5      �� 5   
   -   5         ��Name$ StackPanel3��5   0   $
��Left=�6   $	��Top=�6E   $��
557,97,0,0q�6]   $	��221��6q   $	��173��6~   .+�5      �� -   5        $��0 0 0 20q�5      $	��New��6'   65   �� 5      -	   5        $��0 0 0 20q�5      $��Search��6'   68   �� 5      -
   5        $��0 0 0 20q�5      $	��Add��6'   65   �� 5       -   5        $��0 0 0 20q�5       $
��Edit��6'   66   �� 5!      -   5        $��0 0 0 20q�5!      $��Delete��6'   68   �� 5"      -   5        $��0 0 0 20q�5"      $
��Exit��6'   66   5#      �� 5$   
   -   5         ��Name$ 	errorText��5$   %   $y�Hidden=�6   $��Error Happened ! ��68   $
��Left=�6T   $��557,357,0,0q�6o   $	��Top=�6�   $	��173��6�   ���	#FFEB2020$��9(�6�   5&      5'      