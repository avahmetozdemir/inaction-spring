FROM openjdk:11

COPY target/lawmanInaction.jar lawmanInaction.jar

ENTRYPOINT [ \
"java", \
"-jar", \
"/lawmanInaction.jar"]