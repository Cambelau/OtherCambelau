   M S B A M L   `   `   `  ����F  BAssignment3, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null)clr-namespace:Assignment3Assignment3  5   	   &     Assignment3.GradesManagementForm   5   	   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   +localclr-namespace:Assignment3     5   	     � WindowStartupLocation$  CenterScreen=�5   	    � Title$ GradesManagementForm��5	   	   $	��450��6&   $	��800��63   .��5
      � $��10,0,0,0q�6   .+�5   
   �� $��ID��6   $
��Left=�6   $��	82,52,0,0q�68   $	��Top=�6K    J� RenderTransformOrigin$ -1.147,-2.259&�6c   �� 5   
   $��Name and family��6   $
��Left=�6*   $��	76,95,0,0q�6E   $	��Top=�6X   $ -1.147,-2.259&�6p   $��50��6�   $	��111��6�   �� 5   
   $��Course��6   $
��Left=�6!   $��
76,145,0,0q�6<   $	��Top=�6P   $ -1.147,-2.259&�6h   �� 5   
   $��Grade��6   $
��Left=�6    $��
76,191,0,0q�6;   $	��Top=�6O   $ -1.147,-2.259&�6g   �� 5   
   -   5         ��Name$ StackPanel3��5   0   $
��Left=�6   $	��Top=�6E   $��
577,38,0,0q�6]   $	��221��6q   $	��173��6~   .+�5      �� -   5        $��0 0 0 20q�5      $	��New��6'   65   �� 5      -   5        $��0 0 0 20q�5      $��	Add Grade��6'   6;   �� 5      -   5        $��0 0 0 20q�5      $��Modify Grade��6'   6>   �� 5      -   5        $��0 0 0 20q�5      $��Preview��6'   69   �� 5      -   5        $��0 0 0 20q�5      $
��Exit��6'   66   5      �� 5   
   -   5         ��Name$ nameTextBox��5      $
��Left=�6'   $��
229,99,0,0q�6B    �� TextWrapping$
 Wrap=�6V   $�� ��6j   $	��Top=�6r   $	��120��6�   �� 5   
   -   5         ��Name$ 
IDComboBox��5      $
��Left=�6B   $��
229,55,0,0q�6]   $	��Top=�6q   $	��120��6�   �� 5   
   -	   5        $ gradeTextBox��5      $
��Left=�6(   $��229,199,0,0q�6C   $
 Wrap=�6X   $�� ��6l   $	��Top=�6t   $	��120��6�   �� 5   
   -
   5        $ CourseComboBox��5      $
��Left=�6I   $��229,155,0,0q�6d   $	��Top=�6y   $	��120��6�   &   System.Windows.Controls.DataGrid  5   
   -   5          Name$ dgGrade��5      $��40,242,40,33q�6$   5      5      