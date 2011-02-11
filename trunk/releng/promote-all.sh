#!/bin/bash

# Environment:
JAVA_HOME=/opt/public/common/jdk-1.6.x86_64
ANT_HOME=/opt/public/common/apache-ant-1.7.1
ANT=/opt/public/common/apache-ant-1.7.1/bin/ant

# Workspaces:
NIGHTLY=/shared/jobs/cbi_henshin_nightly/workspace/build/org.eclipse.henshin.releng
RELEASE=/shared/jobs/cbi_henshin_release/workspace/build/org.eclipse.henshin.releng

# Run ant...
$ANT -f $NIGHTLY/promote.xml -Dpromote.properties=$NIGHTLY/promote-N.properties
#$ANT -f $RELEASE/promote.xml -Dpromote.properties=$RELEASE/promote-R.properties
