%%
%1psnr
clc
clear

%a = imread('lenna512.bmp');
%b =imread('im_m3si.bmp');


A = double(a);
B = double(b);
C = A-B;

MSE = sum(C(:).*C(:)) / numel(A);
PSNR = 10*log10(255^2/MSE);%For the peak value use 255

%%
%2.1
clc
clear

I_0 = imread('lenna512.bmp');
A=double(I_0);
B=zeros(256,256);

    for i=1:256
        for j=1:256
        subA=A(((1+2*(i-1)):i*2),((1+2*(j-1)):j*2));
        B(i,j)=mean(subA(:));
        end
    end
    I_1 = uint8(B);
     imshow(I_1,'border','tight','initialmagnification','fit');
     set (gcf,'Position',[0,0,256,256]);
     axis normal;
     %saveas(gca,'I_1.bmp','bmp')
     imwrite(I_1,'I_1.bmp') 
     
     
%%
%2.2
clc
clear

A = imread('im_sp.bmp');
B = double(A);


    for i = 1: 512-3+1
        for j = 1 : 512-3+1
            C = B(i:(i+2), j:(j+2));
            %convert C into a list of vectors for calculation
            C = C(:);
            Cm = mean(C);
            B(i+1, j+1) = Cm;
        end       
    end

im_m3si = uint8(B);
imwrite(im_m3si,"im_m3si.bmp");


%%
%2.3
clc
clear

A = imread('I_1.bmp');

%bilinear I_1_2
B = imresize(A,[512,512],'Method','bilinear');

 I_1_2 = uint8(B);
     %imshow(I_1_2,'border','tight','initialmagnification','fit');
     set (gcf,'Position',[0,0,512,512]);
     axis normal;
     imwrite(I_1_2,'I_1_2.bmp') 

%By default, the Image Processing Toolbox™ function imresize 
% uses the bicubic interpolation algorithm 
C= imresize(A,[512,512]);

 I_1_3 = uint8(C);
     imshow(I_1_3,'border','tight','initialmagnification','fit');
     set (gcf,'Position',[0,0,512,512]);
     axis normal;
     imwrite(I_1_3,'I_1_3.bmp') 
     
     
%%
%3.1
clc
clear


I_0 = imread('Lenna512.bmp');
A = double(I_0);

%zero mean and variance 25, thus standard deviation is 5
B = A + 5*randn(size(I_0));
im_wn = uint8(B);
%imshow(im_wn,'border','tight','initialmagnification','fit');
     %set (gcf,'Position',[0,0,512,512]);
     %axis normal;
     imwrite(im_wn,'im_wn.bmp') 

%%
%3.2
clc
clear

%input m=10/100/1000
A = double(imread('im_wn.bmp'));

function[C] =im_wn(m,A)

 n = 1;
 B = double(zeros(512,512));

    while n <= m
B = B + (A + 5*randn(size(A)));
n=n+1;
    end

C=B/m;
end

%im_wnxx = uint8(C);
%imshow(im_wn4,'border','tight','initialmagnification','fit');
     %set (gcf,'Position',[0,0,512,512]);
     %axis normal;
     imwrite(im_wnxx,'im_wn1000.bmp')

%%
%3.3
clc
clear

%A =imread('lenna512.bmp');

  A = double(imread('lenna512.bmp'));
n = numel(A(:,:,1));
% 15% pixels altered
m = fix(0.15*n);
idx = randperm(n, m);
% among all altered pixels,
% 50% percent of then are now white pixels, others are black pixels
k = fix(0.5*m);
idx1 = idx(1:k);
idx2 = idx(k+1:end);
idx1 = idx1' + n.*(0:size(A,3)-1);
idx1 = idx1(:);
idx2 = idx2' + n.*(0:size(A,3)-1);
idx2 = idx2(:);
A(idx1) = 255;
A(idx2) = 0;

im_sp = uint8(A);
imwrite(im_sp,'im_sp.bmp')


%%
%3.4
clc
clear


%im_sp=imnoise(imread('lenna512.bmp'),'salt & pepper',0.15);
%imwrite(im_sp,'im_sp.bmp')

im_sp = imread('im_sp.bmp');

%3*3
im_median3 = medfilt2(im_sp);
imwrite(im_median3,'im_median3.bmp');

%5*5
im_median5 = medfilt2(im_sp,[5,5]);
imwrite(im_median5,'im_median5.bmp');

%%
%3.5
lc
clear

A = icmread('im_sp.bmp');
B = double(A);


    for i = 1: 512-3+1
        for j = 1 : 512-3+1
            C = B(i:(i+2), j:(j+2));
            %convert C into a list of vectors for calculation
            C = C(:);
            Cm = mean(C);
            B(i+1, j+1) = Cm;
        end       
    end

im_m3si = uint8(B);
imwrite(im_m3si,"im_m3si.bmp");



