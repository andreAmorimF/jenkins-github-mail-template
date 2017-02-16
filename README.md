# jenkins-github-mail-template

A jenkins mail template, based on Groovy, capable of querying GitHub in order to obtain tickets URLs of a given build changelog.

## Requirements

This mail template is based on the [Email-Ext plugin](https://wiki.jenkins-ci.org/display/JENKINS/Email-ext+plugin) and uses jenkins Groovy API to obtain information about commits on the Git changelog. Other extremely useful plugin for using along with this template is the [Managed Script Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Managed+Script+Plugin), used to make it easy to insert this template into a project _Jenkinsfile_.

## Installation

In order to install this template, use the *Managed Script Plugin* to insert this template into your Jenkins as a *Extended Email Publisher Groovy Template*.

## Usage

In your _Jenkinsfile_, in order to obtain the resolved content of the script, reference the managed script such it is indicated on the example bellow:

```
def content = '${SCRIPT, template="managed:jenkins-github-changelog"}'

emailext (
        to: 'destination@whatever.com',
        from: 'source@whatever.com',
        replyTo: 'source@whatever.com',
        subject: "Git changelog!!!",
        body: content,
        attachLog: false,
        mimeType: 'text/html',
)
```

Don't forget to change the reference _baseUrl_ variable on the template in order to indicate your GitHub/GitHub Entreprise base url:

```
<!-- CHANGE ME! -->
def baseUrl="https://github.mycompany.com"
```
