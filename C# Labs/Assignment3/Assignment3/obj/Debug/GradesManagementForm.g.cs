﻿#pragma checksum "..\..\GradesManagementForm.xaml" "{8829d00f-11b8-4213-878b-770e8597ac16}" "48A9D52CE85F62C43097A678405B6E6F027232DA57935A12A97984FCDB483EA5"
//------------------------------------------------------------------------------
// <auto-generated>
//     Ce code a été généré par un outil.
//     Version du runtime :4.0.30319.42000
//
//     Les modifications apportées à ce fichier peuvent provoquer un comportement incorrect et seront perdues si
//     le code est régénéré.
// </auto-generated>
//------------------------------------------------------------------------------

using Assignment3;
using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;


namespace Assignment3 {
    
    
    /// <summary>
    /// GradesManagementForm
    /// </summary>
    public partial class GradesManagementForm : System.Windows.Window, System.Windows.Markup.IComponentConnector {
        
        
        #line 16 "..\..\GradesManagementForm.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.StackPanel StackPanel3;
        
        #line default
        #line hidden
        
        
        #line 23 "..\..\GradesManagementForm.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox nameTextBox;
        
        #line default
        #line hidden
        
        
        #line 24 "..\..\GradesManagementForm.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.ComboBox IDComboBox;
        
        #line default
        #line hidden
        
        
        #line 25 "..\..\GradesManagementForm.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox gradeTextBox;
        
        #line default
        #line hidden
        
        
        #line 26 "..\..\GradesManagementForm.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.ComboBox CourseComboBox;
        
        #line default
        #line hidden
        
        
        #line 27 "..\..\GradesManagementForm.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.DataGrid dgGrade;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/Assignment3;component/gradesmanagementform.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\GradesManagementForm.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            this.StackPanel3 = ((System.Windows.Controls.StackPanel)(target));
            return;
            case 2:
            
            #line 17 "..\..\GradesManagementForm.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.NewButton);
            
            #line default
            #line hidden
            return;
            case 3:
            
            #line 18 "..\..\GradesManagementForm.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.AddButton);
            
            #line default
            #line hidden
            return;
            case 4:
            
            #line 19 "..\..\GradesManagementForm.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.EditButton);
            
            #line default
            #line hidden
            return;
            case 5:
            
            #line 20 "..\..\GradesManagementForm.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.PreviewButton);
            
            #line default
            #line hidden
            return;
            case 6:
            
            #line 21 "..\..\GradesManagementForm.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.exitApplication);
            
            #line default
            #line hidden
            return;
            case 7:
            this.nameTextBox = ((System.Windows.Controls.TextBox)(target));
            return;
            case 8:
            this.IDComboBox = ((System.Windows.Controls.ComboBox)(target));
            
            #line 24 "..\..\GradesManagementForm.xaml"
            this.IDComboBox.DropDownClosed += new System.EventHandler(this.onIdDrop);
            
            #line default
            #line hidden
            return;
            case 9:
            this.gradeTextBox = ((System.Windows.Controls.TextBox)(target));
            return;
            case 10:
            this.CourseComboBox = ((System.Windows.Controls.ComboBox)(target));
            
            #line 26 "..\..\GradesManagementForm.xaml"
            this.CourseComboBox.DropDownClosed += new System.EventHandler(this.onCourseDrop);
            
            #line default
            #line hidden
            return;
            case 11:
            this.dgGrade = ((System.Windows.Controls.DataGrid)(target));
            return;
            }
            this._contentLoaded = true;
        }
    }
}
