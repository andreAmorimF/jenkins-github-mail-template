<%

import hudson.model.Result

// Global variables (Change the baseUrl!!!)
def baseUrl="https://github.mycompany.com"
def parentProject = project.getParent()
def organization = parentProject.getParent()
def buildStatusColor = (build.result == Result.SUCCESS ? "green" : (build.result == Result.ABORTED ? "orange" : "red"))

%>
<STYLE>
BODY, TABLE, TD, TH, P {
  font-family:Verdana,Helvetica,sans serif;
  font-size:11px;
  color:black;
}
h1 { color:black; }
h2 { color:black; }
h3 { color:black; }
TD.bg1 { color:white; background-color:#0000C0; font-size:120% }
TD.bg2 { color:white; background-color:#4040FF; font-size:110% }
TD.bg3 { color:white; background-color:#8080FF; }
TD.test_passed { color:blue; }
TD.test_failed { color:red; }
TD.console { font-family:Courier New; }
</STYLE>
<BODY>

<p> Hello, </p>

<p> The following information regards the production deployment build <b><%= build.properties.environment.BUILD_NUMBER %></b> : </p>

<TABLE>
  <TR><TD class="bg1" colspan="2"><B>BUILD INFORMATION:</B></TD></TR>
  <TR><TD>Status:</TD><TD><b><font color="${buildStatusColor}">${build.result}</font></b></TD</TR>
  <TR><TD>URL:</TD><TD><A href="${rooturl}${build.url}">${rooturl}${build.url}</A></TD></TR>
  <TR><TD>Jenkins Project:</TD><TD>${parentProject.name}</TD></TR>
  <TR><TD>Date:</TD><TD>${it.timestampString}</TD></TR>
  <TR><TD>Duration:</TD><TD>${build.durationString}</TD></TR>
</TABLE>
<BR/>

<!-- CHANGE SET -->
<% def changeSet = build.getChangeSets()
if(changeSet != null && !changeSet.isEmpty()) {%>
        <TABLE width="100%">
    <TR><TD class="bg1" colspan="2"><B>CHANGES PUSHED TO PRODUCTION:</B></TD></TR>
<%      changeSet.each() { cs ->
          cs.each() { change ->
            authorMail =  change.authorEmail
            authorUrl = "${baseUrl}/${change.author}"
            commitId = change.id.substring(0,7)
            comment = change.comment
            ticketUrl = "${baseUrl}/${organization.name}/${parentProject.name}/commit/${change.id}"
%>
      <TR>
        <TD colspan="2" class="bg2">&nbsp;&nbsp;<a href="${ticketUrl}"><B>[<%= commitId %>]</B></a> by
            <a href="${authorUrl}"><B><%= authorMail %>: </B></a>
          <span>${comment}</span>
         </TD>
      </TR>
<%        }
        }
} else {
%>
      <TR><TD colspan="2">No Changes</TD></TR>
<%} %>
  </TABLE>
<BR/>

</BODY>
