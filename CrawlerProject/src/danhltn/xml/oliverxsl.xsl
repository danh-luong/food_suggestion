<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output encoding="UTF-8" method="xml" omit-xml-declaration="yes"/>

    <xsl:template match="/">
        <productlist>
            <xsl:for-each select="//article[@class='template-article template-article--recipe template-article--image-led body-background']">
                <xsl:choose>
                    <xsl:when test="position() > 0">
                        <product>
                            <imageUrl>
                                <xsl:value-of select="div/div/div/div/div/picture/img[contains(@class, 'align size-letterbox_image image-handler__image image-handler__image--aspect no-wrap')]/@data-src"/>
                            </imageUrl>
                            <name>
                                <xsl:value-of select="div/div/div/div/h1[@class='heading-1 template-article__title ']/text()"/>
                            </name>
                            <time>
                                <xsl:value-of select="div/div/div/div/div/ul/li/time[@class='heading-6 recipe-key-data__text']"/>
                            </time>
                            <serves>
                                <xsl:value-of select="div/div/div/div/div/ul/li/span[@itemprop='recipeYield']"/>
                            </serves>
                            <level>
                                <xsl:value-of select="div/div/div/div/div/ul/li/span[not(@itemprop) and @class='heading-6 recipe-key-data__text']"/>
                            </level>
                            <description>
                                <xsl:value-of select="div/div/div/div/div/p[@class='body-copy-large']/text()"/>
                            </description>
                            <ingredients>
                                <xsl:for-each select="div[@class='container']/div/div/div/div/div/section/ul[@class='list-group']/li">
                                    <ingredient>
                                        <xsl:value-of select="span[@class='body-copy']/span[@class='list-group__name']/text()"/>
                                        <xsl:text> </xsl:text>
                                        <xsl:value-of select="span[@class='body-copy']/text()"/>
                                    </ingredient>
                                </xsl:for-each>
                            </ingredients>
                            <steps>
                                <xsl:for-each select="div[@class='container']/div/div/div/div/div/section/section/ul[@class='post-method-steps__collection']/li">
                                    <step>
                                        <xsl:value-of select="p/text()"/>
                                        <xsl:text>: </xsl:text>
                                        <xsl:value-of select="div/body/p/text()"/>
                                    </step>
                                </xsl:for-each>
                            </steps>
                            <cals>
                                <xsl:value-of select="div[@class='container']/div/div/div[@class='layout-md-rail__primary template-article__margin-bottom js-content']/div/div/section[@class='col-12']/nutrition/ul[@itemprop='nutrition']/li/strong"/>
                                <xsl:text>: </xsl:text>
                                <xsl:value-of select="div[@class='container']/div/div/div[@class='layout-md-rail__primary template-article__margin-bottom js-content']/div/div/section[@class='col-12']/nutrition/ul[@itemprop='nutrition']/li/span/span"/>
                            </cals>
                        </product>
                    </xsl:when>
                </xsl:choose>
            </xsl:for-each>
        </productlist>
    </xsl:template>

</xsl:stylesheet>
