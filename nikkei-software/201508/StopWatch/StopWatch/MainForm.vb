Public Class MainForm

    Private startTime As DateTime

    Private lapseTime As TimeSpan = TimeSpan.Zero

    Private Function GetDisplayTimeString() As String
        Dim format As String = "mm\:ss\.f"
        Return lapseTime.ToString(format)
    End Function

    Private Sub UpdateUI()
        displayTimeTextBox.Text = GetDisplayTimeString()
    End Sub

    Private Sub StopTimer()
        timer.Stop()
        UpdateUI()
    End Sub

    Private Sub MainForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        UpdateUI()
    End Sub

    Private Sub timer_Tick(sender As Object, e As EventArgs) Handles timer.Tick
        lapseTime = DateTime.Now - startTime
        UpdateUI()
    End Sub

    Private Sub startButton_Click(sender As Object, e As EventArgs) Handles startButton.Click
        startTime = DateTime.Now
        timer.Start()
    End Sub

    Private Sub stopButton_Click(sender As Object, e As EventArgs) Handles stopButton.Click
        StopTimer()
    End Sub

    Private Sub resetButton_Click(sender As Object, e As EventArgs) Handles resetButton.Click
        lapseTime = TimeSpan.Zero
        StopTimer()
    End Sub
End Class
