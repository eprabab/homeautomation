{{/* vim: set filetype=mustache: */}}
{{/*
Expand the name of the chart.
*/}}
{{- define "homeautomation.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}


{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "homeautomation.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Common labels
*/}}
{{- define "homeautomation.labels" -}}
app.kubernetes.io/name: {{ include "homeautomation.name" . }}
helm.sh/chart: {{ include "homeautomation.chart" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end -}}


{{/*
Connection to messagebuskf
*/}}
{{- define "homeautomation.messagebuskf.connectHost" -}}
{{- printf "%s:%s/" .Values.messagebuskf.clientServiceName  .Values.messagebuskf.clientPort -}}
{{- end -}}

{{/*
Connection to messagebuskf with chroot
*/}}
{{- define "homeautomation.messagebuskf.connect" -}}
{{ template "homeautomation.messagebuskf.connectHost" . }}{{ template "homeautomation.name" . }}
{{- end -}}

{{/*
Image path for the chart
*/}}
{{- define "homeautomation.imagePath" -}}
{{- $registryUrl := .Values.image.url }}
{{- $image := .Values.image.repository -}}
{{- $tag := .Values.image.tag -}}

{{- $imagePath := printf "%s/%s:%s" $registryUrl  $image $tag -}}
{{- print (regexReplaceAll "[/]+" $imagePath "/") -}}
{{- end -}}
