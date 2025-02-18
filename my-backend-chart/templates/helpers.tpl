{{/*
Return the base name of the chart.
*/}}
{{- define "my-backend.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Return the full name of the release.
*/}}
{{- define "my-backend.fullname" -}}
{{- if .Values.fullnameOverride -}}
  {{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
  {{- printf "%s-%s" .Release.Name (include "my-backend.name" .) | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
