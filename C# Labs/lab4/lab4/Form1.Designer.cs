namespace lab4
{
    partial class Form1
    {
        /// <summary>
        /// Variable nécessaire au concepteur.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Nettoyage des ressources utilisées.
        /// </summary>
        /// <param name="disposing">true si les ressources managées doivent être supprimées ; sinon, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Code généré par le Concepteur Windows Form

        /// <summary>
        /// Méthode requise pour la prise en charge du concepteur - ne modifiez pas
        /// le contenu de cette méthode avec l'éditeur de code.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.Connected = new System.Windows.Forms.Button();
            this.Disconnected = new System.Windows.Forms.Button();
            this.Clear = new System.Windows.Forms.Button();
            this.Display = new System.Windows.Forms.Button();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.customerDataSet = new lab4.CustomerDataSet();
            this.tableBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.tableTableAdapter = new lab4.CustomerDataSetTableAdapters.TableTableAdapter();
            this.customerDataSet1 = new lab4.CustomerDataSet1();
            this.customerDataSet1BindingSource = new System.Windows.Forms.BindingSource(this.components);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.customerDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.tableBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.customerDataSet1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.customerDataSet1BindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // Connected
            // 
            this.Connected.Location = new System.Drawing.Point(66, 375);
            this.Connected.Name = "Connected";
            this.Connected.Size = new System.Drawing.Size(117, 37);
            this.Connected.TabIndex = 0;
            this.Connected.Text = "Connected";
            this.Connected.UseVisualStyleBackColor = true;
            this.Connected.Click += new System.EventHandler(this.Connected_Click);
            // 
            // Disconnected
            // 
            this.Disconnected.Location = new System.Drawing.Point(425, 375);
            this.Disconnected.Name = "Disconnected";
            this.Disconnected.Size = new System.Drawing.Size(117, 37);
            this.Disconnected.TabIndex = 2;
            this.Disconnected.Text = "Disconnected";
            this.Disconnected.UseVisualStyleBackColor = true;
            this.Disconnected.Click += new System.EventHandler(this.Disconnected_Click);
            // 
            // Clear
            // 
            this.Clear.Location = new System.Drawing.Point(586, 375);
            this.Clear.Name = "Clear";
            this.Clear.Size = new System.Drawing.Size(117, 37);
            this.Clear.TabIndex = 3;
            this.Clear.Text = "Clear";
            this.Clear.UseVisualStyleBackColor = true;
            this.Clear.Click += new System.EventHandler(this.Clear_Click);
            // 
            // Display
            // 
            this.Display.Location = new System.Drawing.Point(251, 375);
            this.Display.Name = "Display";
            this.Display.Size = new System.Drawing.Size(117, 37);
            this.Display.TabIndex = 4;
            this.Display.Text = "Display";
            this.Display.UseVisualStyleBackColor = true;
            this.Display.Click += new System.EventHandler(this.Display_Click);
            // 
            // dataGridView1
            // 
            this.dataGridView1.AutoGenerateColumns = false;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.DataSource = this.customerDataSet1BindingSource;
            this.dataGridView1.Location = new System.Drawing.Point(35, 12);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 51;
            this.dataGridView1.RowTemplate.Height = 24;
            this.dataGridView1.Size = new System.Drawing.Size(753, 339);
            this.dataGridView1.TabIndex = 5;
            this.dataGridView1.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // customerDataSet
            // 
            this.customerDataSet.DataSetName = "CustomerDataSet";
            this.customerDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // tableBindingSource
            // 
            this.tableBindingSource.DataMember = "Table";
            this.tableBindingSource.DataSource = this.customerDataSet;
            // 
            // tableTableAdapter
            // 
            this.tableTableAdapter.ClearBeforeFill = true;
            // 
            // customerDataSet1
            // 
            this.customerDataSet1.DataSetName = "CustomerDataSet1";
            this.customerDataSet1.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // customerDataSet1BindingSource
            // 
            this.customerDataSet1BindingSource.DataSource = this.customerDataSet1;
            this.customerDataSet1BindingSource.Position = 0;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.Display);
            this.Controls.Add(this.Clear);
            this.Controls.Add(this.Disconnected);
            this.Controls.Add(this.Connected);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.customerDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.tableBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.customerDataSet1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.customerDataSet1BindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button Connected;
        private System.Windows.Forms.Button Disconnected;
        private System.Windows.Forms.Button Clear;
        private System.Windows.Forms.Button Display;
        private System.Windows.Forms.DataGridView dataGridView1;
        private CustomerDataSet customerDataSet;
        private System.Windows.Forms.BindingSource tableBindingSource;
        private CustomerDataSetTableAdapters.TableTableAdapter tableTableAdapter;
        private System.Windows.Forms.BindingSource customerDataSet1BindingSource;
        private CustomerDataSet1 customerDataSet1;
    }
}

