/**
 * 预览区域的响应式工具栏
 */
export default class PreviewerBubble {
    /**
     *
     * @param {import('../Previewer').default} previewer
     * @param {import('../Editor').default} editor
     */
    constructor(previewer: import('../Previewer').default, editor: import('../Editor').default);
    instanceId: string;
    /**
     * @property
     * @type {import('../Previewer').default}
     */
    previewer: import('../Previewer').default;
    /**
     * @property
     * @type {import('../Editor').default}
     */
    editor: import('../Editor').default;
    previewerDom: HTMLDivElement;
    /**
     * @property
     * @type {{ emit: (...args: any[]) => any}}
     */
    bubbleHandler: {
        emit: (...args: any[]) => any;
    };
    init(): void;
    $onClick(e: any): void;
    /**
     * 隐藏预览区域已经激活的工具栏
     */
    $removeAllPreviewerBubbles(): void;
    bubble: HTMLDivElement;
    /**
     * 为选中的图片增加操作工具栏
     * @param {HTMLImageElement} htmlElement 用户点击的图片dom
     */
    $showImgPreviewerBubbles(htmlElement: HTMLImageElement): {
        mouseResize: {};
        getImgPosition(): {
            bottom: number;
            top: number;
            height: any;
            width: any;
            right: number;
            left: number;
            x: number;
            y: number;
        };
        initBubbleButtons(): {
            points: {
                arr: string[];
                arrInfo: {
                    leftTop: {
                        name: string;
                        left: number;
                        top: number;
                    };
                    leftBottom: {
                        name: string;
                        left: number;
                        top: number;
                    };
                    rightTop: {
                        name: string;
                        left: number;
                        top: number;
                    };
                    rightBottom: {
                        name: string;
                        left: number;
                        top: number;
                    };
                    leftMiddle: {
                        name: string;
                        left: number;
                        top: number;
                    };
                    middleBottom: {
                        name: string;
                        left: number;
                        top: number;
                    };
                    middleTop: {
                        name: string;
                        left: number;
                        top: number;
                    };
                    rightMiddle: {
                        name: string;
                        left: number;
                        top: number;
                    };
                };
            };
            imgSrc: any;
            style: {
                width: any;
                height: any;
                left: number;
                top: number;
                marginTop: number;
                marginLeft: number;
            };
            scrollTop: any;
            position: {
                bottom: number;
                top: number;
                height: any;
                width: any;
                right: number;
                left: number;
                x: number;
                y: number;
            };
        };
        showBubble(img: any, container: any, previewerDom: any): void;
        emit(type: any, event?: {}): boolean | void;
        previewUpdate(callback: any): void;
        drawBubbleButs(): void;
        remove(): void;
        updateBubbleButs(): void;
        $updatePointsInfo(): void;
        $getPointsInfo(left: any, top: any): {
            leftTop: {
                left: number;
                top: number;
            };
            leftBottom: {
                left: number;
                top: any;
            };
            rightTop: {
                left: any;
                top: number;
            };
            rightBottom: {
                left: any;
                top: any;
            };
            leftMiddle: {
                left: number;
                top: number;
            };
            middleBottom: {
                left: number;
                top: any;
            };
            middleTop: {
                left: number;
                top: number;
            };
            rightMiddle: {
                left: any;
                top: number;
            };
        };
        $isResizing(): any;
        dealScroll(event: any): void;
        initMouse(): {
            left: number;
            top: number;
            resize: boolean;
            name: string;
        };
        resizeBegin(event: any): boolean;
        resizeStop(event: any, buts: any, editor: any, menu: any): boolean;
        resizeWorking(event: any, buts: any): void;
        change(): void;
        bindChange(func: any): void;
        $getChange(x: number, y: number, type: string): {
            changeX: number;
            changeY: number;
        };
    } | {
        emit: () => void;
    };
    totalImgs: number;
    imgIndex: number;
    beginChangeImgValue(htmlElement: any): boolean;
    imgAppend: any;
    /**
     * 修改图片尺寸时的回调
     * @param {HTMLElement} htmlElement 被拖拽的图片标签
     * @param {Object} style 图片的属性（宽高、对齐方式）
     */
    changeImgValue(htmlElement: HTMLElement, style: any): void;
    $creatPreviewerBubbles(): void;
    $showBorderBubbles(): void;
    $showBtnBubbles(): void;
}
